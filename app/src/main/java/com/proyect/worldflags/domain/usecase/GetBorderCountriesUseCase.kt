package com.proyect.worldflags.domain.usecase

import com.proyect.worldflags.domain.model.CountryPreview
import com.proyect.worldflags.domain.repository.CountryRepository
import com.proyect.worldflags.util.Resource
import javax.inject.Inject

class GetBorderCountriesUseCase @Inject constructor(
    private val repository: CountryRepository
) {
    suspend operator fun invoke(borders: String?): List<CountryPreview> {
        return if (borders.isNullOrEmpty()) {
            emptyList()
        } else {
            val borderList = borders.split(",").map { it.trim() }
            when (val result = repository.getCountriesByFifaCodes(borderList)) {
                is Resource.Success -> result.data.orEmpty()
                else -> emptyList()
            }
        }
    }
}
