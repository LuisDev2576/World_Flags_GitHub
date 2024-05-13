package com.proyect.worldflags.ui.presentation.countryDetails.components


import java.text.NumberFormat
import java.util.Locale

fun formatNumber(input: String): String {
    return try {
        val number = input.toLong()
        val formatter = NumberFormat.getNumberInstance(Locale.GERMANY) // Locale.GERMANY usa puntos como separadores de miles
        formatter.format(number)
    } catch (e: NumberFormatException) {
        "Invalid number"
    }
}