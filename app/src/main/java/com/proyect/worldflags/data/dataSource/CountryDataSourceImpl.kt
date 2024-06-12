package com.proyect.worldflags.data.dataSource

import com.proyect.worldflags.data.local.CountryDao
import com.proyect.worldflags.data.local.CountryEntity
import com.proyect.worldflags.data.local.CountryEntityPreview
import com.proyect.worldflags.domain.dataSource.CountryDataSource
import com.proyect.worldflags.domain.model.CountryPreview
import javax.inject.Inject

class CountryDataSourceImpl @Inject constructor(
    private val countryDao: CountryDao
) : CountryDataSource {

    override suspend fun getAllCountriesPreview(): List<CountryEntityPreview> {
        return countryDao.getAllCountriesPreview()
    }

    override suspend fun upsertCountryList(countryList: List<CountryEntity>) {
        countryDao.upsertCountryList(countryList)
    }

    override suspend fun getCountryById(countryId: String): CountryEntity? {
        return countryDao.getCountryById(countryId)
    }

    override suspend fun getCountriesByFifaCodes(cca3Codes: List<String>): List<CountryPreview> {
        return countryDao.getCountriesByFifaCodes(cca3Codes)
    }
}
