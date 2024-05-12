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

}
