package com.proyect.worldflags.data.repository

import com.proyect.worldflags.data.mappers.toCountry
import com.proyect.worldflags.data.mappers.toCountryEntity
import com.proyect.worldflags.data.mappers.toCountryPreview
import com.proyect.worldflags.data.remote.CountriesApi
import com.proyect.worldflags.domain.repository.CountryRepository
import com.proyect.worldflags.data.local.AppDatabase
import com.proyect.worldflags.domain.model.Country
import com.proyect.worldflags.domain.model.CountryPreview
import com.proyect.worldflags.util.NetworkUtil
import com.proyect.worldflags.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CountryRepositoryImpl @Inject constructor(
    private val countriesApi: CountriesApi,
    private val appDatabase: AppDatabase,
    private val networkUtil: NetworkUtil
) : CountryRepository {

    override suspend fun getAllCountriesPreviews(
        forceFetchFromRemote: Boolean
    ): Flow<Resource<List<CountryPreview>>> = flow {
        emit(Resource.Loading(true))

        val localCountries = getLocalCountries()
        if (localCountries.isNotEmpty() && !forceFetchFromRemote) {
            emit(Resource.Success(data = localCountries))
            emit(Resource.Loading(false))
            return@flow
        }

        if (!networkUtil.isInternetAvailable()) {
            if (localCountries.isEmpty()) {
                emit(Resource.Error(message = "No internet connection and no local data available"))
            } else {
                emit(Resource.Success(data = localCountries))
            }
            emit(Resource.Loading(false))
            return@flow
        }

        val result = fetchRemoteCountries()
        emit(result)
        emit(Resource.Loading(false))
    }

    override suspend fun getLocalCountries(): List<CountryPreview> {
        return appDatabase.countryDao().getAllCountriesPreview().map { it.toCountryPreview() }
    }

    override suspend fun fetchRemoteCountries(): Resource<List<CountryPreview>> {
        return try {
            val countryListFromApi = countriesApi.getAllCountries("name,cca3,capital,flags,population,borders,area,region,languages,currencies,gini,timezones,idd,car")
            val countriesEntity = countryListFromApi.map { it.toCountryEntity() }
            appDatabase.countryDao().upsertCountryList(countriesEntity)
            Resource.Success(data = countriesEntity.map { it.toCountryPreview() })
        } catch (e: Exception) {
            Resource.Error(message = "Error loading countries")
        }
    }

    override suspend fun getCountryById(id: String): Country? {
        val countryEntity = appDatabase.countryDao().getCountryById(id)
        return countryEntity?.toCountry()
    }

    override suspend fun getCountriesByFifaCodes(fifaCodes: List<String>): List<CountryPreview> {
        return appDatabase.countryDao().getCountriesByFifaCodes(fifaCodes)
    }

}
