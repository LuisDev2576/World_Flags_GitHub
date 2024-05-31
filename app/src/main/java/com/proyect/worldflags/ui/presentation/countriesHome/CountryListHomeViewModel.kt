package com.proyect.worldflags.ui.presentation.countriesHome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proyect.worldflags.domain.model.CountryPreview
import com.proyect.worldflags.domain.usecase.GetCountriesUseCase
import com.proyect.worldflags.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryListHomeViewModel @Inject constructor(
    private val getCountriesUseCase: GetCountriesUseCase
) : ViewModel() {

    private val _countriesPreviewsHomeListState = MutableStateFlow(CountriesPreviewsHomeListState())
    val countriesPreviewsHomeListState = _countriesPreviewsHomeListState.asStateFlow()

    init {
        getCountries(false)
    }

    fun getCountries(forceFetchFromRemote: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            _countriesPreviewsHomeListState.update { it.copy(isLoading = true, error = "") }

            getCountriesUseCase(forceFetchFromRemote).collectLatest { result ->
                _countriesPreviewsHomeListState.update {
                    when (result) {
                        is Resource.Error -> it.copy(error = result.message ?: "Unknown error", isLoading = false)
                        is Resource.Success -> it.copy(
                            countriesList = (it.countriesList + result.data.orEmpty().shuffled()).distinct(),
                            isLoading = false,
                            error = ""
                        )
                        is Resource.Loading -> it.copy(isLoading = result.isLoading)
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
