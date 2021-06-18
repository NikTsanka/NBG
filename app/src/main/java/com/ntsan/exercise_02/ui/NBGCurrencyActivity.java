package com.ntsan.exercise_02.ui;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.ntsan.exercise_02.R;
import com.ntsan.exercise_02.data.models.ListItemClass;
import com.ntsan.exercise_02.databinding.ActivityNgbcurrencyBinding;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NBGCurrencyActivity extends AppCompatActivity {

    private static final String url = "https://www.nbg.gov.ge/index.php?m=582";
    private ActivityNgbcurrencyBinding binding;

    private Document doc;
    private Thread secThread;
    private Runnable runnable;
    private ListView listView;

    private CustomArrayAdapter adapter;
    private List<ListItemClass> arrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNgbcurrencyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();
    }

    private void init() {
        listView = binding.listView;
        arrayList = new ArrayList<>();
        adapter = new CustomArrayAdapter(this, R.layout.list_item, arrayList, getLayoutInflater());
        listView.setAdapter(adapter);
        runnable = this::getWebToJson;
        secThread = new Thread(runnable);
        secThread.start();
    }

    private void getWebToJson() {
        try {
            doc = Jsoup.connect(url).get();
            Elements tables = doc.getElementsByTag("tbody");
            Element ourTable = tables.get(54);

//            Elements elsFromTable = ourTable.children();
//            Element USD = elsFromTable.get(40);
//            Elements elsUSD = USD.children();

            Log.d("MyLog", "TBody: " + ourTable.children().text());

            for (int i = 0; i < ourTable.childrenSize(); i++) {
                ListItemClass items = new ListItemClass();
                items.setData1(ourTable.children().get(i).child(1).text());
                items.setData2(ourTable.children().get(i).child(0).text());
                items.setData3(ourTable.children().get(i).child(2).text());
                items.setData4(ourTable.children().get(i).child(4).text());
                arrayList.add(items);
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter.notifyDataSetChanged();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}