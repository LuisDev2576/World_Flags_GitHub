package com.proyect.worldflags.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.proyect.worldflags.ui.presentation.countriesHome.CountryListHomeScreen
import com.proyect.worldflags.ui.presentation.countryDetails.CountryDetailsScreen

@Composable
fun Navigation(){


    val navHostController = rememberNavController()

    NavHost(
        navController = navHostController,
        startDestination = CountriesHome
    ) {
        composable<CountriesHome>{
            CountryListHomeScreen(navController = navHostController)
        }

        composable<CountryDetails> {
            CountryDetailsScreen(navController = navHostController)
        }
    }
}
