package com.ntsan.exercise_02.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ntsan.exercise_02.R
import com.ntsan.exercise_02.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private var startStop: Boolean = false
    private var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

    }

    fun onClickStart(view: View) {
        if (!startStop) {
            binding?.buttonStart?.text = "Stop"
            startStop = true
            Thread {
                while (startStop) {
                    counter++
                    when (counter) {
                        1 -> {
                            binding?.greenL?.setBackgroundColor(resources.getColor(R.color.green_200))
                            binding?.yellowL?.setBackgroundColor(resources.getColor(R.color.gray_500))
                            binding?.redL?.setBackgroundColor(resources.getColor(R.color.gray_500))
                        }
                        2 -> {
                            binding?.greenL?.setBackgroundColor(resources.getColor(R.color.gray_500))
                            binding?.yellowL?.setBackgroundColor(resources.getColor(R.color.yellow_200))
                            binding?.redL?.setBackgroundColor(resources.getColor(R.color.gray_500))
                        }
                        3 -> {
                            binding?.greenL?.setBackgroundColor(resources.getColor(R.color.gray_500))
                            binding?.yellowL?.setBackgroundColor(resources.getColor(R.color.gray_500))
                            binding?.redL?.setBackgroundColor(resources.getColor(R.color.red_200))
                            counter = 0
                        }
                    }
                    Thread.sleep(1000)
                }
            }.start()
        } else {
            startStop = false
            binding?.buttonStart?.text = "Start"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        startStop = false
    }
}