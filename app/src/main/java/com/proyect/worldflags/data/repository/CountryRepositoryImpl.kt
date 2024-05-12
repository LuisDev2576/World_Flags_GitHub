package com.proyect.worldflags.data.repository

import com.proyect.worldflags.data.mappers.toCountry
import com.proyect.worldflags.data.mappers.toCountryEntity
import com.proyect.worldflags.data.mappers.toCountryPreview
import com.proyect.flagy.data.remote.CountriesApi
import com.proyect.worldflags.domain.repository.CountryRepository
import com.proyect.worldflags.data.local.AppDatabase
import com.proyect.worldflags.domain.model.CountryPreview
import com.proyect.worldflags.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CountryRepositoryImpl @Inject constructor(
    private val countriesApi: CountriesApi,
    private val appDatabase: AppDatabase
) : CountryRepository {

    override suspend fun getAllCountriesPreviews(
        forceFetchFromRemote: Boolean
    ): Flow<Resource<List<CountryPreview>>> = flow {
        emit(Resource.Loading(true))

        val localCountries = appDatabase.countryDao().getAllCountriesPreview()
        if (localCountries.isNotEmpty() && !forceFetchFromRemote) {
            emit(Resource.Success(
                data = localCountries.map { countryEntity ->
                    countryEntity.toCountryPreview()
                }
                //.sortedWith(compareBy({ it.population }, { it.commonName }))
            ))
            emit(Resource.Loading(false))
            return@flow
        }

         val countryListFromApi  = try {
            countriesApi.getAllCountries("name,cca3,capital,flags,population,borders,area,region,languages,currencies,gini,timezones,idd,car")
        } catch (e: IOException) {
            e.printStackTrace()
            emit(Resource.Error(message = "Error loading countries"))
            return@flow
        } catch (e: HttpException) {
            e.printStackTrace()
            emit(Resource.Error(message = "Error loading countries"))
            return@flow
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.Error(message = "Error loading countries"))
            return@flow
        }

        val countriesEntity = countryListFromApi.let {
            it.map { country ->
                country.toCountryEntity()
            }
        }

        appDatabase.countryDao().upsertCountryList(countriesEntity)

        emit(Resource.Success(
            countriesEntity.map { it.toCountryPreview() }
            //.sortedWith(compareBy({ it.population }, { it.commonName }))
        ))
        emit(Resource.Loading(false))



    }

}
