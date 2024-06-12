package com.proyect.worldflags.ui.presentation.countryDetails.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.proyect.worldflags.R

@Composable
fun DisplayGiniIndex(giniData: String?) {
    val context = LocalContext.current

    giniData?.let { it ->
        val parts = it.split(":").map {
            it.trim()
        } // Aseguramos eliminar espacios en blanco alrededor

        if (parts.size == 2) {
            val year = parts[0]
            val index = parts[1]

            TextItems(title = context.getString(R.string.gini_index_year, year), subtitle = index)
        } else {
            // En caso de que el formato no sea el esperado, manejar el error o mostrar un valor predeterminado
            TextItems(
                title = context.getString(R.string.gini_index_unknown_format),
                subtitle = null
            )
        }
    } ?: run {
        // En caso de que giniData sea null, puedes manejarlo de manera apropiada
        TextItems(title = context.getString(R.string.gini_index_not_available), subtitle = null)
    }
}
