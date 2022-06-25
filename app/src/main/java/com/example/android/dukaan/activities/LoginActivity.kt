package com.example.android.dukaan.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.android.dukaan.R
import com.example.android.dukaan.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity(), View.OnClickListener {
    lateinit var tv_register: TextView;
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvRegister.setOnClickListener {
            val i = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(i)
//            Toast.makeText(this@LoginActivity, "done", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onClick(v: View?) { // will handle all clicks on the screen
        if (v != null) {
            when (v.id) {
                R.id.tv_forgotPw -> { }
                R.id.btn_login -> { //                    validateLoginDetails()
                }
            } }
    }

    private fun validateRegisterDetails(): Boolean {
        return when {
            TextUtils.isEmpty(binding.etMail.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_email), true)
                false
            }
            TextUtils.isEmpty(binding.etPw.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_password), true)
                false
            }
            else -> {
                showErrorSnackBar("Your details are valid.", false)
                true
            }

        }
    }
}