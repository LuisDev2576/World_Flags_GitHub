package com.proyect.worldflags.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
object CountriesHome

@Serializable
data class CountryDetails(val countryId: String)