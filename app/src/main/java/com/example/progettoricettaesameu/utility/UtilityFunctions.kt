package com.example.progettoricettaesameu.utility

import android.widget.CheckBox

class UtilityFunctions {

    fun tipoPiatto(chksalato: CheckBox, chkdolce: CheckBox): String {
        return when {
            chksalato.isChecked -> chksalato.text.toString()
            chkdolce.isChecked -> chkdolce.text.toString()
            else -> "Nessuna selezione"
        }
    }

    fun continenteScelta(
        chkeuropa: CheckBox,
        chkafrica: CheckBox,
        chkasia: CheckBox,
        chkamerica: CheckBox,
        chkoceania: CheckBox,
        chkantartide: CheckBox
    ): String {
        return when {
            chkeuropa.isChecked -> chkeuropa.text.toString()
            chkafrica.isChecked -> chkafrica.text.toString()
            chkasia.isChecked -> chkasia.text.toString()
            chkamerica.isChecked -> chkamerica.text.toString()
            chkoceania.isChecked -> chkoceania.text.toString()
            chkantartide.isChecked -> chkantartide.text.toString()
            else -> "NON HAI SELEZIONATO UN CONTINENTE"
        }
    }

    fun stringaToList(input: String): List<String> {
        return input.split(",").map { it.trim() }
    }
}


