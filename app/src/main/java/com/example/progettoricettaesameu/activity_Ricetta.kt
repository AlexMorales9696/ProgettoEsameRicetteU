package com.example.progettoricettaesameu

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class activity_Ricetta : AppCompatActivity() {

    private lateinit var textViewExample: TextView
    private var ricettaPresadbdR: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ricetta)

        textViewExample = findViewById(R.id.etRicettaRead)
        ricettaPresadbdR = intent.getStringExtra("NOME_EXTRA") ?: "NON ASSEGNATA"

        val btnIndietroHome2 = findViewById<Button>(R.id.btnRitornaHome2)
        btnIndietroHome2.setOnClickListener {
            navigateTobackHome()
        }
    }

    override fun onResume() {
        super.onResume()
        // Verifica se ci sono dati prima di aggiornare la TextView
        if (ricettaPresadbdR.isNotEmpty()) {
            textViewExample.text = ricettaPresadbdR
        }
    }

    private fun navigateTobackHome() {
        val intent = Intent(this, activity_home::class.java)
        startActivity(intent)
    }
}