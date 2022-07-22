package com.example.android.dukaan.activities

import android.content.Intent
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity
import android.text.TextUtils
import android.view.View.inflate
import android.widget.*
import com.example.android.dukaan.R
import com.example.android.dukaan.activities.firestore.FirestoreClass
import com.example.android.dukaan.activities.models.User
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class RegisterActivity : BaseActivity() {
    private lateinit var tv_login :TextView ;
    private lateinit var et_firstName: EditText;
    private lateinit var et_lastName: EditText;
    private lateinit var et_mail: EditText;
    private lateinit var et_pw: EditText;
    private lateinit var et_rePw: EditText;
    private lateinit var tcChqBox: CheckBox;
    private lateinit var btn_register: Button;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_register)

        et_firstName = findViewById(R.id.et_firstName)
        et_lastName = findViewById(R.id.et_lastName)
        et_mail = findViewById(R.id.et_mail)
        et_pw = findViewById(R.id.et_pw)
        et_rePw = findViewById(R.id.et_rePw)
        tv_login = findViewById(R.id.tv_login)
        tcChqBox = findViewById(R.id.tcChqBox)
        btn_register = findViewById(R.id.btn_register)

        tv_login.setOnClickListener {
            // launch the login screen when the user clicks on the text
            onBackPressed()
        }

        btn_register.setOnClickListener {
            registerUser() // validate user input and register the user to the firebase
        }

    }
    /**
     * A function to validate the entries of a new user.
     */
    private fun validateRegisterDetails(): Boolean {
        return when {
            TextUtils.isEmpty(et_firstName.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_first_name), true)
                false
            }
            TextUtils.isEmpty(et_lastName.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_last_name), true)
                false
            }

            TextUtils.isEmpty(et_mail.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_email), true)
                false
            }

            TextUtils.isEmpty(et_pw.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_password), true)
                false
            }

            TextUtils.isEmpty(et_rePw.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_confirm_password), true)
                false
            }

            et_pw.text.toString().trim { it <= ' ' } != et_rePw.text.toString()
                .trim { it <= ' ' } -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_password_and_confirm_password_mismatch), true)
                false
            }
            !tcChqBox.isChecked -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_agree_terms_and_condition), true)
                false
            }
            else -> {
                //showErrorSnackBar("Your details are valid.", false)
                true
            }
        }
    }


    /**
     * A function to register the user with email and password using FirebaseAuth.
     */
    private fun registerUser() {

        // Check with validate function if the entries are valid or not.
        if (validateRegisterDetails()) {

            showProgressDialog("Please Wait")

            val email: String = et_mail.text.toString().trim { it <= ' ' }
            val password: String = et_pw.text.toString().trim { it <= ' ' }

            // Create an instance and create a register a user with email and password.
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->

                    hideProgressDialog()

                    // If the registration is successfully done
                    if (task.isSuccessful) {

                        // Firebase registered user
                        val firebaseUser: FirebaseUser = task.result!!.user!!
                        val user = User(firebaseUser.uid,
                            et_firstName.text.toString().trim{it<=' '},
                            et_lastName.text.toString().trim{it<=' '},
                            et_mail.text.toString().trim{it<=' '},
                            et_lastName.text.toString().trim{it<=' '},
                        )
                        FirestoreClass().registerUser(this@RegisterActivity,user)
//                        FirebaseAuth.getInstance().signOut() // to signout
//                        finish()

                    } else {

                        // If the registering is not successful then show error message.
                        showErrorSnackBar(task.exception!!.message.toString(), true)
                    }
                }
        }
    }
    fun userRegistrationSuccess() {

        // Hide the progress dialog
        hideProgressDialog()
        Toast.makeText( this@RegisterActivity, resources.getString(R.string.register_success), Toast.LENGTH_SHORT).show()
    }

}
