package com.example.learnandroidstudio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val label = findViewById<TextView>(R.id.main_label)
        val userData: EditText = findViewById(R.id.user_data)
        val button: Button = findViewById(R.id.button)

        button.setOnClickListener{
            val text = userData.text.toString().trim()
            if (text == "toast")
                Toast.makeText(this, "Useer enter toast", Toast.LENGTH_SHORT).show()
            else
                label.text = text
        }
    }
}