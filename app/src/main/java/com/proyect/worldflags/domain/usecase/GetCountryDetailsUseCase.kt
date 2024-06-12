package com.proyect.worldflags.domain.usecase

import com.proyect.worldflags.domain.model.Country
import com.proyect.worldflags.domain.repository.CountryRepository
import com.proyect.worldflags.util.Resource
import javax.inject.Inject

class GetCountryDetailsUseCase @Inject constructor(
    private val repository: CountryRepository
) {
    suspend operator fun invoke(id: String): Country? {
        return when (val result = repository.getCountryById(id)) {
            is Resource.Success -> result.data
            else -> null
        }
    }
}
