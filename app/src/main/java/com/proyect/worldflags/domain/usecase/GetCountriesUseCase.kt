package com.proyect.worldflags.domain.usecase

import com.proyect.worldflags.domain.model.CountryPreview
import com.proyect.worldflags.domain.repository.CountryRepository
import com.proyect.worldflags.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCountriesUseCase @Inject constructor(
    private val countryRepository: CountryRepository
) {
    suspend operator fun invoke(forceFetchFromRemote: Boolean): Flow<Resource<List<CountryPreview>>> {
        return countryRepository.getAllCountriesPreviews(forceFetchFromRemote)
    }
}
