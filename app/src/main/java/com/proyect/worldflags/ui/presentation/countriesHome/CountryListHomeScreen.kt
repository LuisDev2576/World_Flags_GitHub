package com.proyect.worldflags.ui.presentation.countriesHome

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.proyect.worldflags.R
import com.proyect.worldflags.ui.presentation.countriesHome.components.CountryList
import com.proyect.worldflags.ui.presentation.countriesHome.components.CountryListError
import com.proyect.worldflags.ui.presentation.countriesHome.components.LogoImage
import com.proyect.worldflags.ui.presentation.countriesHome.components.SearchBar
import com.proyect.worldflags.ui.presentation.countriesHome.components.ShimmerListItem

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.CountryListHomeScreen(
    navController: NavHostController,
    viewModel: CountryListHomeViewModel = hiltViewModel(),
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    var searchText by rememberSaveable { mutableStateOf("") }
    val countriesListState = viewModel.countriesListState.collectAsState().value
    val isDarkTheme = isSystemInDarkTheme()
    val imageRes = if (isDarkTheme) R.drawable.logo_dark else R.drawable.logo_light
    val imageState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageRes)
            .size(Size.ORIGINAL)
            .build()
    ).state

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LogoImage(imageState)

        imageState.painter?.let {
            SearchBar(searchText = searchText, onValueChange = { searchText = it })
            when (countriesListState) {
                is CountryListHomeViewModel.CountriesListState.Loading -> {
                    LazyColumn(
                        contentPadding = PaddingValues(vertical = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.fillMaxSize(),
                        userScrollEnabled = false
                    ) {
                        items(30) {
                            ShimmerListItem()
                        }
                    }
                }
                is CountryListHomeViewModel.CountriesListState.Success -> {
                    CountryList(
                        navController = navController,
                        countriesList = countriesListState.countries,
                        searchText = searchText,
                        animatedVisibilityScope = animatedVisibilityScope
                    )
                }
                is CountryListHomeViewModel.CountriesListState.Error -> {
                    CountryListError(
                        error = countriesListState.message,
                        refresh = {
                            viewModel.getCountries(true)
                        }
                    )
                }
            }
        }
    }
}
