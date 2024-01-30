package com.example.progettoricettaesameu

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.firestore.FirebaseFirestore
import com.example.progettoricettaesameu.utility.UtilityFunctions

private lateinit var chkdolceRead: CheckBox
private lateinit var chksalatoRead: CheckBox
private lateinit var chkeuropaRead: CheckBox
private lateinit var chkafricaRead: CheckBox
private lateinit var chkasiaRead: CheckBox
private lateinit var chkamericaRead: CheckBox
private lateinit var chkoceaniaRead: CheckBox
private lateinit var chkantartideRead: CheckBox
private val utilityFunctions = UtilityFunctions()
private var ingredientiScelti: String = ""

class activity_readRicetta : AppCompatActivity() {

    val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_ricetta)
        //bottoni per mostrare ricerca e tornare alla schermata iniziale
        val btnRicercaFinale = findViewById<Button>(R.id.btnRead)
        val btnIndietroHome = findViewById<Button>(R.id.btnIndietroHome)
        val analtycs = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString("message", "app")
        analtycs.logEvent("mainavc", bundle)
        btnRicercaFinale.setOnClickListener { LeggeredaDB() }
        btnIndietroHome.setOnClickListener { navigateToback() }

    }

    private fun LeggeredaDB() {
        var textViewExample = findViewById<TextView>(R.id.etRicettaRead)
        chkdolceRead = findViewById(R.id.chkSalatoFinale)
        chksalatoRead = findViewById(R.id.chkDolceFinale)
        chkeuropaRead = findViewById(R.id.chkEuropaFinale)
        chkafricaRead = findViewById(R.id.chkAfricaFinle)
        chkasiaRead = findViewById(R.id.chkAsiaFinale)
        chkamericaRead = findViewById(R.id.chkAmericaFinale)
        chkoceaniaRead = findViewById(R.id.chkOceaniaFinale)
        chkantartideRead = findViewById(R.id.chkAntartideFinale)
        val sceltaTRead = utilityFunctions.tipoPiatto(chkdolceRead, chksalatoRead)
        val continenteTRead = utilityFunctions.continenteScelta(
            chkeuropaRead,
            chkafricaRead,
            chkasiaRead,
            chkamericaRead,
            chkoceaniaRead,
            chkantartideRead
        )

        val ingredientiR1 = findViewById<EditText>(R.id.etIngredientiRicercaDB)
        val ingredientiRdb1 = ingredientiR1.text.toString()

        if (chkdolceRead.isChecked && chksalatoRead.isChecked) {
            Toast.makeText(
                this,
                "Devi selezionare solo una delle due checkbox (Salato o Dolce)",
                Toast.LENGTH_SHORT
            ).show()
            return
        } else if (!chkdolceRead.isChecked && !chksalatoRead.isChecked) {
            Toast.makeText(
                this,
                "Devi selezionare almeno una delle due checkbox (Salato o Dolce)",
                Toast.LENGTH_SHORT
            ).show()
            return
        }
        var numeroContinentiSelezionati = 0

        if (chkeuropaRead.isChecked) {
            numeroContinentiSelezionati++
        }
        if (chkafricaRead.isChecked) {
            numeroContinentiSelezionati++
        }
        if (chkasiaRead.isChecked) {
            numeroContinentiSelezionati++
        }
        if (chkamericaRead.isChecked) {
            numeroContinentiSelezionati++
        }
        if (chkoceaniaRead.isChecked) {
            numeroContinentiSelezionati++
        }
        if (chkantartideRead.isChecked) {
            numeroContinentiSelezionati++
        }

        if (numeroContinentiSelezionati != 1) {
            Toast.makeText(this, "Devi selezionare esattamente un continente", Toast.LENGTH_SHORT)
                .show()
            return
        }

        // Controllo se l'EditText è vuoto
        if (ingredientiRdb1.isBlank()) {
            Toast.makeText(this, "Devi inserire almeno un ingredienti", Toast.LENGTH_SHORT).show()
            return
        }


        val docRef = db.collection("ricette")
            .whereArrayContainsAny("Ingredienti", listOf(ingredientiRdb1))
            .whereEqualTo("tipoPiatto", sceltaTRead)
            .whereEqualTo("continente", continenteTRead)
            .get()
            .addOnSuccessListener { result ->
                var ricettaPresadb = "" // Dichiarare e inizializzare qui
                for (document in result) {
                    ricettaPresadb += "Nome Piatto: ${document.get("nomePiatto").toString()}\n"
                    ricettaPresadb += "Procedimento Ricetta: ${
                        document.get("procedimentoRicetta").toString()
                    }\n"
                }

                Log.d("activity_readRicetta", "leerSele - ricettaPresadb: $ricettaPresadb")
                if (ricettaPresadb.isEmpty()) {
                    textViewExample.text = "non abbiamo ricette con queste dati"
                } else {
                    textViewExample.text = ricettaPresadb
                }
                chkdolceRead.isChecked = false
                chksalatoRead.isChecked = false
                chkeuropaRead.isChecked = false
                chkafricaRead.isChecked = false
                chkasiaRead.isChecked = false
                chkamericaRead.isChecked = false
                chkoceaniaRead.isChecked = false
                chkantartideRead.isChecked = false
                ingredientiR1.setText("")

            }
            .addOnFailureListener { exception ->
                Log.d("TAG", "leerSele - error: $exception")
            }
    }

    private fun navigateToback() {
        val intent = Intent(this, activity_home::class.java)
        startActivity(intent)
    }

    public fun SperaIngredienti() {
        Log.d("Tag", "Valore di ingredientiRdb: $ingredientiScelti")
    }
}
