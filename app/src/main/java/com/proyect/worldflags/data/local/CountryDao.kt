package com.proyect.worldflags.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.proyect.worldflags.domain.model.CountryPreview

@Dao
interface CountryDao {

    @Query("SELECT id, population, commonName, capital, pngFlagUrl FROM CountryEntity")
    suspend fun getAllCountriesPreview(): List<CountryEntityPreview>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertCountryList(countryList: List<CountryEntity>)

    @Query("SELECT * FROM CountryEntity WHERE id = :countryId")
    suspend fun getCountryById(countryId: String): CountryEntity?

    // Método nuevo para obtener países por códigos cca3
    @Query("SELECT id, population, commonName, capital, pngFlagUrl FROM CountryEntity WHERE cca3 IN (:cca3Codes)")
    suspend fun getCountriesByFifaCodes(cca3Codes: List<String>): List<CountryPreview>

}
