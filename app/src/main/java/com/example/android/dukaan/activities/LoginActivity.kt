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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginActivity : BaseActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvForgotPw.setOnClickListener {
            startActivity(Intent(this@LoginActivity, ForgotPasswordActivity::class.java))
        }

        // btn login pressed
        binding.btnLogin.setOnClickListener{
            loginRegisteredUser()}

        // user dont have an account so redirect to register activity
        binding.tvRegister.setOnClickListener{
            val i = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(i)
        }
    }

    /**
     * A function to validate the entries of an user.
     */
    private fun validateLoginDetails(): Boolean {
        return when {
            TextUtils.isEmpty(binding.etMail.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_email), true)
                false
            }
            TextUtils.isEmpty(binding.etPw.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_password), true)
                false
            } else -> { true }
        }
    }
    /**
     * A function to login the user with email and password using FirebaseAuth.
     */
    private fun loginRegisteredUser() {
        // Check with validate function if the entries are valid or not.
        if (validateLoginDetails()) {
            showProgressDialog("Please Wait")

            val email: String = binding.etMail.text.toString().trim { it <= ' ' }
            val password: String = binding.etPw.text.toString().trim { it <= ' ' }

            // Create an instance and create a register a user with email and password.
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    // hiding the progress dialog
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