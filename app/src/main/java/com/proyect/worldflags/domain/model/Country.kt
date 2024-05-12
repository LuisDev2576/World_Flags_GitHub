package com.proyect.worldflags.domain.model

import javax.annotation.concurrent.Immutable

@Immutable
data class Country(
    val id: String,
    val commonName: String,
    val officialName: String,
    val capital: String?,
    val pngFlagUrl: String,
    val svgFlagUrl: String,
    val population: Long,
    val borders: String?,
    val area: Double,
    val region: String,
    val languages: String,
    val currencies: String,
    val gini: String?,
    val timeZone: String,
    val dialingCode: String,
    val driveSide: String,
    val cca3: String
)