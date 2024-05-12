package com.proyect.worldflags.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CountryEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val commonName: String,
    val officialName: String,
    val capital: String?,
    val pngFlagUrl: String,
    val svgFlagUrl: String,
    val population: Long,
    val borders: String?,
    val area: Double,
    val cca3: String,
    val region: String,
    val languages: String,
    val currencies: String,
    val gini: String?,
    val timeZone: String,
    val dialingCode: String,
    val driveSide: String
)