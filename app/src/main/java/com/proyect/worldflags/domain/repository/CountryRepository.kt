package com.proyect.worldflags.domain.repository

import kotlinx.coroutines.flow.Flow
import com.proyect.worldflags.domain.model.CountryPreview
import com.proyect.worldflags.domain.model.Country
import com.proyect.worldflags.util.Resource

interface CountryRepository {
    suspend fun getAllCountriesPreviews(forceFetchFromRemote: Boolean): Flow<Resource<List<CountryPreview>>>
    suspend fun getCountryById(id: String): Country?
    suspend fun getCountriesByFifaCodes(fifaCodes: List<String>): List<CountryPreview>
}
