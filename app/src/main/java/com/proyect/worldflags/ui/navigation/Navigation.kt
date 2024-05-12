package com.proyect.worldflags.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.proyect.worldflags.ui.presentation.countriesHome.CountryListHomeScreen

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

    }
}
