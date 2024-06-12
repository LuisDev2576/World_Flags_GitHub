package com.proyect.worldflags.ui.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.proyect.worldflags.ui.presentation.countriesHome.CountryListHomeScreen
import com.proyect.worldflags.ui.presentation.countryDetails.CountryDetailsScreen

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun Navigation() {
    SharedTransitionLayout {
        val navHostController = rememberNavController()

        NavHost(
            navController = navHostController,
            startDestination = CountriesHome
        ) {
            composable<CountriesHome> {
                CountryListHomeScreen(
                    navController = navHostController,
                    animatedVisibilityScope = this
                )
            }

            composable<CountryDetails> {
                CountryDetailsScreen(
                    navController = navHostController,
                    animatedVisibilityScope = this
                )
            }
        }
    }
}
