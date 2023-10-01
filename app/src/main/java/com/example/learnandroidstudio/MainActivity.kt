package com.example.learnandroidstudio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userLogin : TextView = findViewById(R.id.user_login)
        val userEmail : TextView = findViewById(R.id.user_email)
        val userPass: TextView = findViewById(R.id.user_pass)
    }
}