package com.proyect.worldflags.ui.presentation.countriesHome.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.proyect.worldflags.R
import com.proyect.worldflags.ui.navigation.CountryDetails
import com.proyect.worldflags.ui.presentation.countriesHome.CountryListHomeViewModel

@Composable
fun CountryList(
    navController: NavHostController,
    countriesListState: CountryListHomeViewModel.CountriesPreviewsHomeListState,
    searchText: String,
    refresh: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    val filteredList = countriesListState.countriesList
        .filter { it.commonName.contains(searchText, ignoreCase = true) }
        .sortedByDescending { it.population }

    when {
        countriesListState.error.isNotBlank() -> {
            CountryListError(
                error = countriesListState.error,
                refresh = {
                    refresh()
                }
            )
        }
        countriesListState.isLoading -> {
            LazyColumn(
                contentPadding = PaddingValues(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxSize(),
                userScrollEnabled = false
            ) {
                items(30){
                    ShimmerListItem()
                }
            }
        }
        else -> {
            LazyColumn(
                contentPadding = PaddingValues(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(filteredList) { country ->
                    CountryListItem(
                        flag = country.pngFlagUrl,
                        name = country.commonName,
                        capital = country.capital ?: "No especificado",
                        onclick = {
                            focusManager.clearFocus()
                            navController.navigate(CountryDetails(country.id))
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}