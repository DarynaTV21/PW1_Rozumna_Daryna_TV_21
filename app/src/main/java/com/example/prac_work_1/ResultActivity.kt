package com.example.prac_work_1

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity

class ResultActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val resultText = intent.getStringExtra("RESULT_TEXT")

        val resultTv: TextView = findViewById(R.id.result_tv)
        val backBtn: Button = findViewById(R.id.btn_back)

        resultTv.text = resultText

        backBtn.setOnClickListener {
            finish() // повертає до MainActivity
        }
    }
}
