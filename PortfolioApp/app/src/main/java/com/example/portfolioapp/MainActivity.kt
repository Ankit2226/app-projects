package com.example.portfolioapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val  buttonskils = findViewById<Button>(R.id.btnskills)

        buttonskils.setOnClickListener {
            intent = Intent(this,SkillsActivity::class.java)
            startActivity(intent)
        }
    }
}