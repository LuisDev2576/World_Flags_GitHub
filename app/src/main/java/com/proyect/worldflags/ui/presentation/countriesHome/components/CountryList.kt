package com.proyect.worldflags.ui.presentation.countriesHome.components

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.proyect.worldflags.R
import com.proyect.worldflags.domain.model.CountryPreview
import com.proyect.worldflags.ui.navigation.CountryDetails

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.CountryList(
    navController: NavHostController,
    countriesList: List<CountryPreview>,
    searchText: String,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    val filteredList = countriesList
        .filter { it.commonName.contains(searchText, ignoreCase = true) }
        .sortedByDescending { it.population }

    LazyColumn(
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(filteredList) { country ->
            CountryListItem(
                flag = country.pngFlagUrl,
                name = country.commonName,
                capital = country.capital ?: context.getString(R.string.not_specified),
                onclick = {
                    focusManager.clearFocus()
                    navController.navigate(CountryDetails(country.id))
                },
                modifier = Modifier.fillMaxWidth(),
                animatedVisibilityScope = animatedVisibilityScope
            )
        }
    }
}
