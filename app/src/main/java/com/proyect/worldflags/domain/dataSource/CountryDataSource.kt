package com.proyect.worldflags.domain.dataSource

import com.proyect.worldflags.data.local.CountryEntity
import com.proyect.worldflags.data.local.CountryEntityPreview
import com.proyect.worldflags.domain.model.CountryPreview

interface CountryDataSource {
    suspend fun getAllCountriesPreview(): List<CountryEntityPreview>
    suspend fun upsertCountryList(countryList: List<CountryEntity>)
    suspend fun getCountryById(countryId: String): CountryEntity?
    suspend fun getCountriesByFifaCodes(cca3Codes: List<String>): List<CountryPreview>
}
