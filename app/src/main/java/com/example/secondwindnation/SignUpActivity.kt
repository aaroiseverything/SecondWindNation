package com.example.secondwindnation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class SignUpActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        auth = FirebaseAuth.getInstance()

        signUp.setOnClickListener{
            signUpUser()
        }
    }
    private fun signUpUser(){
        if (email.text.toString().isEmpty()){
            email.error = "Please Enter Email"
            email.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()){
            email.error = "Please Enter Valid Email"
            email.requestFocus()
            return
        }
        if (password.text.toString().isEmpty()){
            password.error = "Please Enter Password"
            password.requestFocus()
            return
        }
        auth.createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(baseContext, "Sign Up failed, Try again later",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }
}
