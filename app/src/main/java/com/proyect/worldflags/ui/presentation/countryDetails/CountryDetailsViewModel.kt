package com.proyect.worldflags.ui.presentation.countryDetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proyect.worldflags.domain.model.Country
import com.proyect.worldflags.domain.model.CountryPreview
import com.proyect.worldflags.domain.repository.CountryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryDetailsViewModel @Inject constructor(
    private val countryRepository: CountryRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val countryId: String = savedStateHandle["countryId"] ?: throw IllegalArgumentException("countryId is required")

    private val _countryDetailState = MutableStateFlow<CountryDetailState>(CountryDetailState.Loading)
    val countryDetailState: StateFlow<CountryDetailState> = _countryDetailState.asStateFlow()

    init {
        fetchCountryDetails(countryId)
    }

    private fun fetchCountryDetails(id: String) {
        viewModelScope.launch {
            _countryDetailState.value = CountryDetailState.Loading
            try {
                val country = countryRepository.getCountryById(id)
                if (country != null) {
                    val borderCountries = fetchBorderCountries(country.borders)
                    _countryDetailState.value = CountryDetailState.Success(country, borderCountries)
                } else {
                    _countryDetailState.value = CountryDetailState.Error("Country not found")
                }
            } catch (e: Exception) {
                _countryDetailState.value =
                    CountryDetailState.Error("Failed to load country details")
            }
        }
    }

    private suspend fun fetchBorderCountries(borders: String?): List<CountryPreview> {
        return borders?.split(",")?.let { fifaCodes ->
            countryRepository.getCountriesByFifaCodes(fifaCodes.map { it.trim() })
        } ?: emptyList()
    }

    sealed class CountryDetailState {
        object Loading : CountryDetailState()
        data class Success(val country: Country, val borderCountries: List<CountryPreview>) : CountryDetailState()
        data class Error(val message: String) : CountryDetailState()
    }
}
