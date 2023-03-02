package com.gateway.marvel.ui.screen.details


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gateway.marvel.data.utility.MarvelResult
import com.gateway.marvel.data.utility.MarvelState
import com.gateway.marvel.repository.MarvelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: MarvelRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {


    var selectedCategory by mutableStateOf("")
        private set


    var uiState by mutableStateOf(MarvelState())
        private set

    var isLoading by mutableStateOf(false)
        private set


    init {
        val category = savedStateHandle.get<String>("category")

        selectedCategory = category!!

        getData(MarvelCategories.valueOf(selectedCategory))
    }

    private fun getData(category: MarvelCategories) {
        viewModelScope.launch {
            handleResponse(repository.getDetailsData(category))
        }
    }


    private fun handleResponse(response: MarvelResult) {

        isLoading = true


        when (response) {
            is MarvelResult.Error -> {
                uiState = uiState.copy(
                    error = response.message
                )
                isLoading = false
            }
            MarvelResult.Loading -> {
                uiState = uiState.copy(
                    isLoading = true
                )

            }
            is MarvelResult.Success -> {
                uiState = uiState.copy(
                    marvelData = response.data,
                    isLoading = false
                )

                isLoading = false
            }
        }
    }
}


enum class MarvelCategories {
    Characters,
    Comics,
    Series,
    Stories,
    Events,
    Cartoons
}



