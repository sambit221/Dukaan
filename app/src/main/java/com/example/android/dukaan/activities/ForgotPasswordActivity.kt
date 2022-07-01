package com.example.android.dukaan.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android.dukaan.R
import com.example.android.dukaan.databinding.ActivityForgotPasswordBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class ForgotPasswordActivity : BaseActivity() {
    private lateinit var binding: ActivityForgotPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupActionBar()
        binding.btnSubmit.setOnClickListener {
            val email:String = binding.etMail.text.toString().trim{it<=' '}
            if(email.isEmpty()){
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_email), true)
            }else{ showProgressDialog("Please Wait")
                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                    .addOnCompleteListener{ task->
                        hideProgressDialog()
                        // If the registration is successfully done
                        if (task.isSuccessful) {
                            showErrorSnackBar("You are logged in successfully.", false)
                            // TODO - send user to main activity
                        } else {
                            // If the registering is not successful then show error message.
                            showErrorSnackBar(task.exception!!.message.toString(), true)
                        }
                    }
            }
        }
    }
    // to setup actionbar
    private fun setupActionBar() {
        setSupportActionBar(binding.toolbarForgotPasswordActivity)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_white_back_arrow_24)
        }
        binding.toolbarForgotPasswordActivity.setNavigationOnClickListener { onBackPressed() }
    }
}