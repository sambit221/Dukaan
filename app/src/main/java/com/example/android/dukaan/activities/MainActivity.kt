package com.example.android.dukaan.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.android.dukaan.R
import com.example.android.dukaan.activities.utils.Constants

class MainActivity : AppCompatActivity() {
    private lateinit var tvMain: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvMain = findViewById(R.id.tv_main)

        val sharedPreferences = getSharedPreferences(Constants.Dukaan_Preferences, Context.MODE_PRIVATE)
        val userName = sharedPreferences.getString(Constants.Logged_In_UserName, "")
        tvMain.text="Hello $userName."
    }
}