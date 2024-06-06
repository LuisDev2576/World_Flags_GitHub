package com.proyect.worldflags.ui.presentation.countryDetails

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.proyect.worldflags.ui.navigation.CountriesHome
import com.proyect.worldflags.ui.presentation.countryDetails.components.BorderCountries
import com.proyect.worldflags.ui.presentation.countryDetails.components.FlagImage
import com.proyect.worldflags.ui.presentation.countryDetails.components.ItemDetails
import com.proyect.worldflags.ui.presentation.countryDetails.components.MyTopAppBar

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.CountryDetailsScreen(
    navController: NavHostController,
    viewModel: CountryDetailsViewModel = hiltViewModel(),
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    val state = viewModel.countryDetailState.collectAsState().value

    when (state) {
        is CountryDetailsViewModel.CountryDetailState.Loading -> {Box(modifier = Modifier.background(MaterialTheme.colorScheme.background).fillMaxSize())}

        is CountryDetailsViewModel.CountryDetailState.Success -> {

            Scaffold(
                topBar = { MyTopAppBar(onclick = { navController.navigate(CountriesHome) }, countryName = state.country.commonName, animatedVisibilityScope = animatedVisibilityScope) },

                content = {
                    Column(modifier = Modifier.padding(it).padding(horizontal = 16.dp)) {
                        FlagImage(contentDescription = state.country.commonName, image = state.country.pngFlagUrl, animatedVisibilityScope = animatedVisibilityScope)
                        ItemDetails(country = state.country)
                        BorderCountries(borderCountries = state.borderCountries, navController = navController, animatedVisibilityScope = animatedVisibilityScope)
                    }
                }
            )

        }

        is CountryDetailsViewModel.CountryDetailState.Error -> {}
    }
}




