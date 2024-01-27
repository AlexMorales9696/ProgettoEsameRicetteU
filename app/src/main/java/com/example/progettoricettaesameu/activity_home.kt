package com.example.progettoricettaesameu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class activity_home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val btnAddRicetta =findViewById<Button>(R.id.addButton)
        btnAddRicetta.setOnClickListener{navigateToLogin()}
    }
    private fun navigateToLogin(){
        val intent= Intent(this,activity_addRicetta::class.java)
        startActivity(intent)
    }

}