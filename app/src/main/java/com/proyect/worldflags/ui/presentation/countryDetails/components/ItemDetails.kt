package com.proyect.worldflags.ui.presentation.countryDetails.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.proyect.worldflags.R
import com.proyect.worldflags.domain.model.Country

@Composable
fun ItemDetails(
    country: Country
) {
    val context = LocalContext.current
    Spacer(modifier = Modifier.height(16.dp))

    TextItems(
        title = context.getString(R.string.population),
        subtitle = formatNumber(country.population.toString(), context)
    )
    TextItems(
        title = context.getString(R.string.area),
        subtitle = "${formatNumber(country.area.toInt().toString(), context)} ${context.getString(
            R.string.km2
        )}"
    )

    Spacer(modifier = Modifier.height(16.dp))

    TextItems(title = context.getString(R.string.region), subtitle = country.region)
    TextItems(
        title = context.getString(R.string.capital),
        subtitle = country.capital ?: context.getString(R.string.not_specified)
    )

    Spacer(modifier = Modifier.height(16.dp))

    TextItems(title = context.getString(R.string.time_zone), subtitle = country.timeZone)
    TextItems(title = context.getString(R.string.drive_side), subtitle = country.driveSide)

    Spacer(modifier = Modifier.height(16.dp))

    DisplayGiniIndex(country.gini)

    Spacer(modifier = Modifier.height(16.dp))
}
