package com.proyect.worldflags.data.remote.response

data class CountryResponse(
    val name: Name,
    val capital: List<String>?,
    val flags: Flags,
    val population: Long,
    val borders: List<String>?,
    val area: Double,
    val cca3: String,
    val region: String,
    val languages: Map<String, String>,
    val currencies: Map<String, Currency>,
    val gini: Map<String, Double>?,
    val timezones: List<String>,
    val idd: Idd,
    val car: Car
)

data class Currency(
    val name: String,
    val symbol: String
)

data class Idd(
    val root: String,
    val suffixes: List<String>
)

data class Car(
    val signs: List<String>,
    val side: String
)

data class Name(
    val common: String,
    val official: String
)

data class Flags(
    val png: String,
    val svg: String
)