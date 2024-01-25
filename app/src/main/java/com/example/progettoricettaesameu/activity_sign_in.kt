package com.example.progettoricettaesameu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth


class activity_sign_in : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        val registerButton: Button = findViewById(R.id.btnCreateAccount)
        FirebaseApp.initializeApp(this)
        //auth =Firebase.auth
        auth =FirebaseAuth.getInstance()
        //auth
        registerButton.setOnClickListener { perfromSingUp() }


    }

    //lets get email and password from the user
    private fun perfromSingUp() {

        val email = findViewById<EditText>(R.id.etEmail)
        val password = findViewById<EditText>(R.id.etPassword)



        if (email.text!!.isEmpty() || password.text!!.isEmpty()) {
            Toast.makeText(this, "Pleas fill all fields", Toast.LENGTH_SHORT)
                .show()
            return
        }

        val inputEmail = email.text.toString()
        val inputPassword = password.text.toString()

        auth.createUserWithEmailAndPassword(inputEmail, inputPassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    //next the activity:main

                    val intent = Intent(this, activity_home::class.java)
                    startActivity(intent)
                    Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show()
                } else {
                    //if sing in false, display a messanger tu the user
                    Toast.makeText(this, "Error occurred", Toast.LENGTH_SHORT).show()
                }


            }
    }

}