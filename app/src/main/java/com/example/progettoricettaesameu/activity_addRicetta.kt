package com.example.progettoricettaesameu

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.firestore.FirebaseFirestore


class activity_addRicetta : AppCompatActivity() {

    val db = FirebaseFirestore.getInstance()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_ricetta)
        val addButton: Button =findViewById(R.id.addButton)
        val analtycs= FirebaseAnalytics.getInstance(this)
        val bundle=Bundle()
        bundle.putString("message","app")
        analtycs.logEvent("mainavc",bundle)
        addButton.setOnClickListener{aggiungidb()}


    }
    fun aggiungidb(){
        val ingredientiR = findViewById<EditText>(R.id.etIngredientiR)
        val ingredientiRdb= ingredientiR.text.toString()
        val procedimentoR = findViewById<EditText>(R.id.etRicetta)
        val procediemntoRdb= procedimentoR.text.toString()
        val nomeR = findViewById<EditText>(R.id.etNomeRicetta)
        val nomeRdb= nomeR.text.toString()


        val ricetta = hashMapOf(
            "tipoPiatto" to "",
            "continente" to  "String?=null,",
            "Ingredienti" to (ingredientiRdb),
            "procedimentoRicetta" to (procediemntoRdb),
            "nomePiatto" to (nomeRdb),
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

}