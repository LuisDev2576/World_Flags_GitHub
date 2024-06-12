package com.proyect.worldflags.domain.usecase

import com.proyect.worldflags.domain.model.CountryPreview
import com.proyect.worldflags.domain.repository.CountryRepository
import com.proyect.worldflags.util.Resource
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetCountriesUseCase @Inject constructor(
    private val repository: CountryRepository
) {
    operator fun invoke(forceFetchFromRemote: Boolean): Flow<Resource<List<CountryPreview>>> = flow {
        emit(Resource.Loading())
        val result = repository.getAllCountriesPreviews(forceFetchFromRemote)
        emit(result)
    }.flowOn(Dispatchers.IO)
}
