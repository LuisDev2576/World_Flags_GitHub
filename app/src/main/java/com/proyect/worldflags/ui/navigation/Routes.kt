package com.proyect.worldflags.ui.navigation

sealed class Routes(val routes: String){

    object CountryListHomeScreen: Routes("countryListHomeScreen")

    object CountryDetailScreen: Routes("countryDetailScreen")

    fun withArgs(vararg args: String) :String {
        return buildString {
            append(routes)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
