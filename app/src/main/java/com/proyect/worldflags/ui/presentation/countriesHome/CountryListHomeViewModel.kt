package com.proyect.worldflags.ui.presentation.countriesHome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proyect.worldflags.domain.model.CountryPreview
import com.proyect.worldflags.domain.repository.CountryRepository
import com.proyect.worldflags.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryListHomeViewModel @Inject constructor(
    private val countryRepository: CountryRepository
) : ViewModel() {

    private var _countriesPreviewsHomeListState = MutableStateFlow(CountriesPreviewsHomeListState())
    val countriesPreviewsHomeListState = _countriesPreviewsHomeListState.asStateFlow()

    init {
        getCountries(false)
    }

    fun getCountries(forceFetchFromRemote: Boolean) {
        viewModelScope.launch {
            _countriesPreviewsHomeListState.update {
                it.copy(isLoading = true)
            }

            countryRepository.getAllCountriesPreviews(
                forceFetchFromRemote
            ).collectLatest { result ->
                when (result) {
                    is Resource.Error -> {
                        _countriesPreviewsHomeListState.update {
                            result.message?.let { it1 -> it.copy(error = it1, isLoading = false) }!!
                        }
                    }

                    is Resource.Success -> {
                        result.data?.let { countriesPreviewsList ->
                            _countriesPreviewsHomeListState.update {
                                it.copy(
                                    countriesList = (countriesPreviewsHomeListState.value.countriesList + countriesPreviewsList.shuffled()).distinct(),
                                    isLoading = false,
                                    error = ""
                                )
                            }
                        }
                    }

                    is Resource.Loading -> {
                        _countriesPreviewsHomeListState.update {
                            it.copy(isLoading = result.isLoading)
                        }
                    }
                }
            }
        }
    }

    data class CountriesPreviewsHomeListState(
        val isLoading: Boolean = false,
        val error: String = "",
        val countriesList: List<CountryPreview> = emptyList(),
    )
}
