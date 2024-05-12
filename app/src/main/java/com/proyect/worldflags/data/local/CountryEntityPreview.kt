package com.proyect.worldflags.data.local

import androidx.room.PrimaryKey

data class CountryEntityPreview(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val commonName: String,
    val population: Long,
    val capital: String?,
    val pngFlagUrl: String
)