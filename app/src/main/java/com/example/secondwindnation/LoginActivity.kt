package com.example.secondwindnation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()
        signUp.setOnClickListener{
            startActivity(Intent(this, SignUpActivity::class.java))
            finish()
        }
        buttonAdd.setOnClickListener{
            abuser()
        }
    }
    private fun abuser(){
        val name = editText.text.toString()
        val spinner = spinner.selectedItem.toString()

        if(name.isNotEmpty()){
            val ref = FirebaseDatabase.getInstance().getReference("user")
            val userId = ref.push().key.toString()   //push creates a unique key
            val user = User(userId, name, spinner)
            ref.child(userId).setValue(user)

        }else{
            Toast.makeText(this, "You should enter a name", Toast.LENGTH_LONG).show()
            return
        }
    }
    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }
    private fun updateUI(currentUser: FirebaseUser?){

    }
}
