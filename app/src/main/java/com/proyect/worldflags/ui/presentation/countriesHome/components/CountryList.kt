package com.proyect.worldflags.ui.presentation.countriesHome.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.proyect.worldflags.ui.navigation.Routes
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

    if(countriesListState.error != ""){
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            Text(text = countriesListState.error)
        }
    }
    if(countriesListState.isLoading){
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            CircularProgressIndicator()
        }
    }else{
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
                        navController.navigate(Routes.CountryDetailScreen.withArgs(country.id))
                    },
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}
