package com.ntsan.exercise_02.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ntsan.exercise_02.data.models.Item
import com.ntsan.exercise_02.databinding.ActivityMyCurrencyBinding
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.IOException

class MyCurrencyActivity : AppCompatActivity() {

    private var binding: ActivityMyCurrencyBinding? = null

    private var doc: Document? = null
    private var secThread: Thread? = null
    private var runnable: Runnable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyCurrencyBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        init()
    }

    private var dataList = mutableListOf<Item>()

    private fun init() {
        runnable = Runnable { getWebToJson() }
        secThread = Thread(runnable)
        secThread?.start()
    }

    private fun getWebToJson() {
        try {
            doc = Jsoup.connect(url).get()
            val tables = doc?.getElementsByTag("tbody")
            val ourTable = tables?.get(54)
            val elFromTable = ourTable?.children()
            val USD = elFromTable?.get(40)
            val elUSD = USD?.children()

            Log.d("MyLog", "tbody: " + elUSD?.get(1)?.text())
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    companion object {
        private const val url = "https://www.nbg.gov.ge/index.php?m=582"
    }

}