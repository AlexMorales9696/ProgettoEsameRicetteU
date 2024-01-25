package com.example.progettoricettaesameu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class activity_introduction : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_introduction)
        val btnLogin =findViewById<Button>(R.id.btnLogin)
        val btnSingIn=findViewById<Button>(R.id.btnSingIn)
        btnLogin.setOnClickListener { navigateToLogin() }
        btnSingIn.setOnClickListener { navigateTSingIn() }

    }
    private fun navigateToLogin(){
        val intent= Intent(this,activity_Login::class.java)
        startActivity(intent)
    }
    private fun navigateTSingIn(){
        val intent= Intent(this,activity_sign_in::class.java)
        startActivity(intent)
    }

}