package com.gateway.marvel.ui.screen.details


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gateway.marvel.data.domain.model.Characters
import com.gateway.marvel.data.domain.model.Data
import com.gateway.marvel.data.utility.Resource
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


    var uiState by mutableStateOf(Data())
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


    private fun handleResponse(response: Resource<List<Characters>>) {

        response.let {
            when (it) {
                is Resource.Error -> {
                    //TODO: Show a SnackBar or Toast message
                }
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    uiState = uiState.copy(
                        marvelData = it.data
                    )
                }
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



