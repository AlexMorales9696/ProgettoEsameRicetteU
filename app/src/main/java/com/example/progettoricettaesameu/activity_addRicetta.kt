package com.example.progettoricettaesameu


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import com.example.progettoricettaesameu.utility.UtilityFunctions
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.firestore.FirebaseFirestore

private lateinit var chksalato: CheckBox
private lateinit var chkdolce: CheckBox
private lateinit var chkeuropa: CheckBox
private lateinit var chkafrica: CheckBox
private lateinit var chkasia: CheckBox
private lateinit var chkamerica: CheckBox
private lateinit var chkoceania: CheckBox
private lateinit var chkantartide: CheckBox
private val utilityFunctions = UtilityFunctions()

class activity_addRicetta : AppCompatActivity() {

    val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_ricetta)
        val addButton: Button = findViewById(R.id.addButton)
        val analtycs = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString("message", "app")
        analtycs.logEvent("mainavc", bundle)
        addButton.setOnClickListener {
            aggiungidb()
            navigateToHome()
        }
    }

    fun aggiungidb() {
        val ingredientiR = findViewById<EditText>(R.id.etIngredientiR)
        val procedimentoR = findViewById<EditText>(R.id.etRicetta)
        val nomeR = findViewById<EditText>(R.id.etNomeRicetta)

        chkdolce = findViewById(R.id.chkDolce)
        chksalato = findViewById(R.id.chkSalato)
        chkeuropa = findViewById(R.id.chkEuropa)
        chkafrica = findViewById(R.id.chkAfrica)
        chkasia = findViewById(R.id.chkAsia)
        chkamerica = findViewById(R.id.chkAmerica)
        chkoceania = findViewById(R.id.chkOceania)
        chkantartide = findViewById(R.id.chkAntartide)

        // Controlli sulle checkbox Dolce e Salato
        if (chkdolce.isChecked && chksalato.isChecked) {
            Toast.makeText(
                this,
                "Devi selezionare solo una delle due checkbox (Salato o Dolce)",
                Toast.LENGTH_SHORT
            ).show()
            return
        } else if (!chkdolce.isChecked && !chksalato.isChecked) {
            Toast.makeText(
                this,
                "Devi selezionare almeno una delle due checkbox (Salato o Dolce)",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        // Controlli sui continenti
        val continentiSelezionati =
            listOf(chkeuropa, chkafrica, chkasia, chkamerica, chkoceania, chkantartide)
                .filter { it.isChecked }

        if (continentiSelezionati.size != 1) {
            Toast.makeText(this, "Devi selezionare esattamente un continente", Toast.LENGTH_SHORT)
                .show()
            return
        }

        // Controlli sugli inputText
        if (ingredientiR.text.isNullOrBlank() || procedimentoR.text.isNullOrBlank() || nomeR.text.isNullOrBlank()) {
            Toast.makeText(this, "Tutti i campi devono essere compilati", Toast.LENGTH_SHORT).show()
            return
        }

        val sceltaT = utilityFunctions.tipoPiatto(chksalato, chkdolce)
        val sceltaContinente = utilityFunctions.continenteScelta(
            chkeuropa, chkafrica, chkasia, chkamerica, chkoceania, chkantartide
        )

        val listaDiStringhe = utilityFunctions.stringaToList(ingredientiR.text.toString())

        val ricetta = hashMapOf(
            "tipoPiatto" to sceltaT,
            "continente" to sceltaContinente,
            "Ingredienti" to listaDiStringhe,
            "procedimentoRicetta" to procedimentoR.text.toString(),
            "nomePiatto" to nomeR.text.toString()
        )

        db.collection("ricette")
            .add(ricetta)
            .addOnSuccessListener { documentReference ->
                println("Documento aggiunto con ID")
            }
            .addOnFailureListener { e ->
                println("Errore durante l'aggiunta del documento")
            }
    }

    private fun navigateToHome() {
        val intent = Intent(this, activity_home::class.java)
        startActivity(intent)
    }

}