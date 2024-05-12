package com.proyect.worldflags.ui.presentation.countriesHome.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.proyect.worldflags.ui.presentation.countriesHome.CountryListHomeViewModel

@Composable
fun CountryList(
    navController: NavHostController,
    countriesListState: CountryListHomeViewModel.CountriesPreviewsHomeListState,
    searchText: String
) {
    val focusManager = LocalFocusManager.current
    val filteredList = countriesListState.countriesList
        .filter { it.commonName.contains(searchText, ignoreCase = true) }
        .sortedByDescending { it.population }

    LazyColumn(
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        items(filteredList){country ->
            CountryListItem(
                flag = country.pngFlagUrl,
                name = country.commonName,
                capital = country.capital ?: "No especificado",
                onclick = {
                    focusManager.clearFocus()
                },
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}
