package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var progr = 60
    private var progr2 = 90
    private var progress_bar : ProgressBar? = null
    private var progress_bar2 : ProgressBar? = null
    private var tv_progress :TextView?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progress_bar = findViewById(R.id.progress_bar)
        progress_bar2 = findViewById(R.id.progress_bar2)
        tv_progress = findViewById(R.id.text_view_progress)

        updateProgressBar1()
        updateProgressBar2()

    }

    private fun updateProgressBar1() {
        progress_bar!!.progress = progr
        tv_progress!!.text = "$progr%"
    }

    private fun updateProgressBar2() {
        progress_bar2!!.progress = progr2
        tv_progress!!.text = "$progr2%"
    }
}