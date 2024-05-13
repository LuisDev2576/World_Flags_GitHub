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
        startDestination = Routes.CountryListHomeScreen.routes
    ) {

        composable(Routes.CountryListHomeScreen.routes,){
            CountryListHomeScreen(navController = navHostController)
        }

        composable(
            Routes.CountryDetailScreen.routes + "/{countryId}",
            arguments = listOf(
                navArgument("countryId") {
                    type = NavType.StringType
                }
            )
        ) {
            CountryDetailsScreen(navHostController)
        }
    }
}
