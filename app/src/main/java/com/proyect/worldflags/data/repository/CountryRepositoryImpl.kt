package com.proyect.worldflags.data.repository

import android.content.Context
import com.proyect.worldflags.R
import com.proyect.worldflags.data.mappers.toCountry
import com.proyect.worldflags.data.mappers.toCountryEntity
import com.proyect.worldflags.data.mappers.toCountryPreview
import com.proyect.worldflags.data.remote.CountriesApi
import com.proyect.worldflags.domain.dataSource.CountryDataSource
import com.proyect.worldflags.domain.model.Country
import com.proyect.worldflags.domain.model.CountryPreview
import com.proyect.worldflags.domain.repository.CountryRepository
import com.proyect.worldflags.util.NetworkUtil
import com.proyect.worldflags.util.Resource
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CountryRepositoryImpl
@Inject
constructor(
    private val countriesApi: CountriesApi,
    private val countryDataSource: CountryDataSource,
    private val networkUtil: NetworkUtil,
    @ApplicationContext private val context: Context
) : CountryRepository {
    override suspend fun getAllCountriesPreviews(
        forceFetchFromRemote: Boolean
    ): Resource<List<CountryPreview>> {
        return try {
            val localCountries = getLocalCountries()
            if (localCountries is Resource.Success && localCountries.data?.isNotEmpty() == true &&
                !forceFetchFromRemote
            ) {
                return Resource.Success(localCountries.data)
            }

            if (!networkUtil.isInternetAvailable()) {
                return if (localCountries is Resource.Success &&
                    localCountries.data?.isEmpty() == true
                ) {
                    Resource.Error(context.getString(R.string.no_internet_no_data))
                } else {
                    localCountries
                }
            }

            fetchRemoteCountries()
        } catch (e: Exception) {
            Resource.Error(context.getString(R.string.error_loading_countries))
        }
    }

    override suspend fun getLocalCountries(): Resource<List<CountryPreview>> {
        return try {
            val localCountries = countryDataSource.getAllCountriesPreview().map {
                it.toCountryPreview()
            }
            Resource.Success(localCountries)
        } catch (e: Exception) {
            Resource.Error(context.getString(R.string.error_loading_countries))
        }
    }

    override suspend fun fetchRemoteCountries(): Resource<List<CountryPreview>> {
        return try {
            val countryListFromApi = countriesApi.getAllCountries(
                "name,cca3,capital,flags,population,borders,area,region," +
                    "languages,currencies,gini,timezones,idd,car"
            )
            val countriesEntity = countryListFromApi.map { it.toCountryEntity() }
            countryDataSource.upsertCountryList(countriesEntity)
            Resource.Success(data = countriesEntity.map { it.toCountryPreview() })
        } catch (e: Exception) {
            Resource.Error(context.getString(R.string.error_loading_countries))
        }
    }

    override suspend fun getCountryById(id: String): Resource<Country> {
        return try {
            val countryEntity = countryDataSource.getCountryById(id)
            if (countryEntity != null) {
                Resource.Success(countryEntity.toCountry())
            } else {
                Resource.Error(context.getString(R.string.country_not_found))
            }
        } catch (e: Exception) {
            Resource.Error(context.getString(R.string.failed_to_load_country_details))
        }
    }

    override suspend fun getCountriesByFifaCodes(
        fifaCodes: List<String>
    ): Resource<List<CountryPreview>> {
        return try {
            val countries = countryDataSource.getCountriesByFifaCodes(fifaCodes)
            Resource.Success(countries)
        } catch (e: Exception) {
            Resource.Error(context.getString(R.string.error_loading_countries))
        }
    }
}
