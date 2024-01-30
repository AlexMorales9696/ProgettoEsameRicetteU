package com.example.progettoricettaesameu
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class activity_Login : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
  //fare il link per andare se non sei registrato

        val loginButton:Button=findViewById(R.id.btnLogin)
        FirebaseApp.initializeApp(this)
        //auth =Firebase.auth
        auth =FirebaseAuth.getInstance()
        val tvForgotPassword = findViewById<AppCompatTextView>(R.id.tvForgotPassword)

        tvForgotPassword.setOnClickListener {
            // Aggiungi qui l'azione che desideri eseguire quando il TextView viene cliccato
            // Per esempio, puoi aprire un'altra attività, visualizzare un messaggio, ecc.
            // Esempio: Aprire un'altra attività
            val intent = Intent(this, activity_sign_in::class.java)
            startActivity(intent)
        }
        loginButton.setOnClickListener {performLogin() }

    }
    private fun performLogin(){
     //lets get input frmo the user
        val email = findViewById<EditText>(R.id.etEmail)
        val password = findViewById<EditText>(R.id.etPassword)

        if (email.text!!.isEmpty() || password.text!!.isEmpty()) {
            Toast.makeText(this, "Pleas fill all fields", Toast.LENGTH_SHORT)
                .show()
            return
        }

        val inputEmail = email.text.toString()
        val inputPassword = password.text.toString()

        auth.signInWithEmailAndPassword(inputEmail, inputPassword)
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