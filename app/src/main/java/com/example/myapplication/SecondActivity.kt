package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

class SecondActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)

        val returnHomeButton: android.widget.Button? = findViewById(R.id.button_return_home)

        returnHomeButton?.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            // Опционально: флаги для очистки стека и предотвращения дублирования MainActivity
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
            // finish() // Раскомментируйте, если хотите закрыть SecondActivity при переходе
        }
    }
}