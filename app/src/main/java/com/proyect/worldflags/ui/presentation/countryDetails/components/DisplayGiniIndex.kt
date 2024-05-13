package com.proyect.worldflags.ui.presentation.countryDetails.components


import androidx.compose.runtime.Composable

@Composable
fun DisplayGiniIndex(giniData: String?) {
    giniData?.let {
        val parts = it.split(":").map { it.trim() }  // Aseguramos eliminar espacios en blanco alrededor

        if (parts.size == 2) {
            val year = parts[0]
            val index = parts[1]

            TextItems(title = "Índice de Gini a $year: ", subtitle = index)
        } else {
            // En caso de que el formato no sea el esperado, manejar el error o mostrar un valor predeterminado
            TextItems(title = "Índice de Gini: ", subtitle = "Formato desconocido")
        }
    } ?: run {
        // En caso de que giniData sea null, puedes manejarlo de manera apropiada
        TextItems(title = "Índice de Gini: ", subtitle = "No disponible")
    }
}
