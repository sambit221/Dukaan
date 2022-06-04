package com.example.android.dukaan.activities

import android.content.Intent
import android.os.Build
//import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
//import android.view.WindowInsets
//import android.view.WindowManager
import com.example.android.dukaan.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
//            Toast.makeText(this@SplashActivity, "opening main sc", Toast.LENGTH_SHORT).show()
            finish()// when activity need to be closed
        }, 1500)


    }
}