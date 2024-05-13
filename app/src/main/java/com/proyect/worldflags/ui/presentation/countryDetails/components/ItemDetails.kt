package com.proyect.worldflags.ui.presentation.countryDetails.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.proyect.worldflags.domain.model.Country

@Composable
fun ItemDetails(
    country: Country
){
    Spacer(modifier = Modifier.height(16.dp))

    TextItems(title = "Población: ", subtitle = formatNumber(country.population.toString()))
    TextItems(title = "Area: ", subtitle ="${formatNumber(country.area.toInt().toString())} Km2")

    Spacer(modifier = Modifier.height(16.dp))

    TextItems(title = "Region: ", subtitle = country.region)
    TextItems(title = "Capital: ", subtitle = country.capital?: "No especificado")

    Spacer(modifier = Modifier.height(16.dp))

    TextItems(title = "Zona horaría: ", subtitle = country.timeZone)
    TextItems(title = "Lado de conducción: ", subtitle = country.driveSide)

    Spacer(modifier = Modifier.height(16.dp))

    DisplayGiniIndex(country.gini)

    Spacer(modifier = Modifier.height(16.dp))
}
