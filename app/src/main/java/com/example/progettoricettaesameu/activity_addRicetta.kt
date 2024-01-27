package com.example.progettoricettaesameu


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat

class activity_addRicetta : AppCompatActivity() {
    private var isMaleSelected: Boolean = false
    private var  isFemaleSelected: Boolean = true
    private var cont : Int=0;

    private lateinit var viewMale: CardView
    private lateinit var viewFemale: CardView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_ricetta)
        initComponent()
        initListener()
        initUI()


    }


    private fun initComponent(){
        viewMale= findViewById(R.id.viewSalato)
        viewFemale = findViewById(R.id.viewDolce)
    }
    private fun initListener() {
        viewMale.setOnClickListener {
            changeTipo()
            setTipoColor()
        }

        viewFemale.setOnClickListener {
            changeTipo()
            setTipoColor()
        }
    }


    private fun changeTipo() {
        isMaleSelected = !isMaleSelected
        isFemaleSelected = !isFemaleSelected
    }

    private fun setTipoColor() {
        viewMale.setCardBackgroundColor(getBackgroundColor(isMaleSelected))
        viewFemale.setCardBackgroundColor(getBackgroundColor(isFemaleSelected))
    }
    private fun getBackgroundColor(isSelectedComponent: Boolean): Int {

        val colorReference = if (isSelectedComponent) {
            R.color.background_component_selected
        } else {
            R.color.background_component
        }

        return ContextCompat.getColor(this, colorReference)
    }

    private fun initUI() {
        setTipoColor()
    }


}