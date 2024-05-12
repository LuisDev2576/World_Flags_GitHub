package com.proyect.worldflags.data.mappers


import com.proyect.worldflags.data.local.CountryEntity
import com.proyect.worldflags.data.local.CountryEntityPreview
import com.proyect.worldflags.data.remote.response.CountryResponse
import com.proyect.worldflags.domain.model.Country
import com.proyect.worldflags.domain.model.CountryPreview
import java.util.UUID

fun CountryEntity.toCountry(): Country {
    return Country(
        id = id,
        commonName = commonName,
        officialName = officialName,
        capital = capital,
        pngFlagUrl = pngFlagUrl,
        svgFlagUrl = svgFlagUrl,
        population = population,
        borders = borders,
        area = area,
        region = region,
        languages = languages,
        currencies = currencies,
        gini = gini,
        timeZone = timeZone,
        dialingCode = dialingCode,
        driveSide = driveSide,
        cca3 = cca3
    )
}

fun CountryEntityPreview.toCountryPreview(): CountryPreview {
    return CountryPreview(
        id = id,
        commonName = commonName,
        capital = capital,
        population = population,
        pngFlagUrl = pngFlagUrl,
    )
}

fun CountryEntity.toCountryPreview(): CountryPreview {
    return CountryPreview(
        id = id,
        commonName = commonName,
        capital = capital,
        population = population,
        pngFlagUrl = pngFlagUrl,
    )
}



fun CountryResponse.toCountryEntity(): CountryEntity {

    return CountryEntity(
        id = UUID.randomUUID().toString(),
        commonName = name.common,
        officialName = name.official,
        capital = capital?.firstOrNull(),
        pngFlagUrl = flags.png,
        svgFlagUrl = flags.svg,
        population = population,
        borders = if (borders.isNullOrEmpty()) { null } else { borders.joinToString(",")},
        area = area,
        cca3 = cca3,
        region = region,
        languages = languages.entries.joinToString(",") { "${it.key}:${it.value}" },
        currencies = currencies.entries.joinToString(",") { "${it.key}:${it.value.name} (${it.value.symbol})" },
        gini = if (gini.isNullOrEmpty()) { null } else { gini.entries.joinToString(", ") { "${it.key}: ${it.value}" } },
        timeZone = timezones.joinToString(","),
        dialingCode = "${idd.root}${idd.suffixes.joinToString(",")}",
        driveSide = car.side
    )
}
