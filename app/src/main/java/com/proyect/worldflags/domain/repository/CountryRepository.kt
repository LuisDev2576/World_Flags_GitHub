package com.proyect.worldflags.domain.repository

import com.proyect.worldflags.domain.model.Country
import com.proyect.worldflags.domain.model.CountryPreview
import com.proyect.worldflags.util.Resource

interface CountryRepository {
    suspend fun getAllCountriesPreviews(forceFetchFromRemote: Boolean): Resource<List<CountryPreview>>
    suspend fun getCountryById(id: String): Resource<Country>
    suspend fun getCountriesByFifaCodes(fifaCodes: List<String>): Resource<List<CountryPreview>>
    suspend fun getLocalCountries(): Resource<List<CountryPreview>>
    suspend fun fetchRemoteCountries(): Resource<List<CountryPreview>>
}
