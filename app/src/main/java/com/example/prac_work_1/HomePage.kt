package com.example.prac_work_1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity

class HomePage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val calc1Btn: Button = findViewById(R.id.btn_calc1)
        val calc2Btn: Button = findViewById(R.id.btn_calc2)

        calc1Btn.setOnClickListener {
            // переход на MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        calc2Btn.setOnClickListener {
            // переход на другую страницу (например SecondActivity)
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }
}
