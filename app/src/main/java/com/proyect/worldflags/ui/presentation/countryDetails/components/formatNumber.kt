package com.proyect.worldflags.ui.presentation.countryDetails.components


import android.content.Context
import com.proyect.worldflags.R
import java.text.NumberFormat
import java.util.Locale

fun formatNumber(input: String, context: Context): String {
    return try {
        val number = input.toLong()
        val formatter = NumberFormat.getNumberInstance(Locale.GERMANY) // Locale.GERMANY usa puntos como separadores de miles
        formatter.format(number)
    } catch (e: NumberFormatException) {
        context.getString(R.string.invalid_number)
    }
}
