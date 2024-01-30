package com.example.progettoricettaesameu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.content.ContextCompat.startActivity

class activity_home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val btnAddRicetta =findViewById<Button>(R.id.addButton)
        val btnreadRicetta =findViewById<Button>(R.id.rdButton)
        btnAddRicetta.setOnClickListener{navigateToLogin()}
       btnreadRicetta.setOnClickListener{navigateToReadRicetta()}
    }


    private fun navigateToLogin(){
        val intent= Intent(this,activity_addRicetta::class.java)
        startActivity(intent)
    }
    private fun navigateToReadRicetta(){
        val intent= Intent(this,activity_readRicetta::class.java)
        startActivity(intent)
    }

}