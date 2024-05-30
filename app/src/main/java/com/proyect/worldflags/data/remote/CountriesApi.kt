package com.proyect.worldflags.data.remote

import com.proyect.worldflags.data.remote.response.CountryResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CountriesApi {

    @GET("v3.1/all")
    suspend fun getAllCountries(
        @Query("fields") fields: String = "name,cca3,capital,flags,population,borders,area,region,languages,currencies,gini,timezones,idd,car"
    ): List<CountryResponse>

    companion object {
        const val BASE_URL = "https://restcountries.com/"
    }
}
