package com.proyect.worldflags.domain.model

import javax.annotation.concurrent.Immutable

@Immutable
data class CountryPreview(
    val id: String,
    val commonName: String,
    val capital: String?,
    val population: Long,
    val pngFlagUrl: String,
)