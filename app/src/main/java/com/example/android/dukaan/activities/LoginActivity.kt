package com.example.android.dukaan.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.android.dukaan.R

class LoginActivity : AppCompatActivity() {
//    private var binding: ActivityLoginBinding = ActivityLoginBinding.inflate(layoutInflater)
    lateinit var tv_register :TextView ;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        tv_register = findViewById(R.id.tv_register)
        tv_register.setOnClickListener {
            val i = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(i)
//            Toast.makeText(this@LoginActivity, "done", Toast.LENGTH_SHORT).show()
        }
    }
}