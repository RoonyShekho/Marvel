package com.gateway.marvel.ui.screen.details


import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gateway.marvel.data.domain.model.*
import com.gateway.marvel.data.domain.use_cases.UseCase
import com.gateway.marvel.data.utility.Resource
import com.gateway.marvel.repository.MarvelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val useCase: UseCase,
    private val repository: MarvelRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {


    var selectedCategory by mutableStateOf("")


    var uiState = mutableStateOf(MarvelResponse())
        private set


    init {
        val category = savedStateHandle.get<String>("category")

        category?.let {
            selectedCategory = it
            Log.d("DetailsViewModel", "category: $it")
            getData(it)
        }
    }


    private fun getData(category: String) {
        when (category) {
            "Characters" -> {
                Log.d("DetailsViewModel", "characters")
                getCharacters()
            }
    /*        "Comics" -> {
                getComics()
                Log.d("DetailsViewModel", "comics")
            }
            "Series" -> {
                getSeries()
                Log.d("DetailsViewModel", "series")
            }
            "Stories" -> {
                Log.d("DetailsViewModel", "stories")
                getStories()
            }
            "Events" -> {
                getEvents()
            }*/

        }
    }

   /* private fun getStories() {
        viewModelScope.launch {
            val storiesResponse = useCase.getStoriesUseCase()

            storiesResponse.map {
                handleResponse(it)
            }


            Log.d("DetailsViewModel", "data:$storiesResponse")
        }
    }

    private fun getSeries() {
        viewModelScope.launch {
            val storiesResponse = useCase.getStoriesUseCase()

            handleResponse(storiesResponse)

            Log.d("DetailsViewModel", "data:$storiesResponse")
        }
    }
*/
    private suspend fun handleResponse(response: Flow<Resource<MarvelResponse>>) {

        response.collect {
            when (it) {
                is Resource.Error -> {
                    Log.d("DetailsViewModel", it.message!!)
                    //TODO: Show a SnackBar or Toast message
                }
                is Resource.Loading -> {
                    //TODO: Show a progress indicator
                }
                is Resource.Success -> {
                    Log.d("DetailsViewModel", "success: ${it.data}")
                    uiState.value = it.data!!
                }
            }
        }
    }


    private fun getCharacters() {

        viewModelScope.launch {
            val charactersResponse =  useCase.getCharactersUseCase()

            val repoResponse = repository.getCharacters()

            repoResponse.let{
                Log.d("DetailsViewModel", it.data.toString())
            }


            charactersResponse.let{
                Log.d("DetailsViewModel", "getCharacters: $it")
                uiState.value = it.data!!
            }

        /*    handleResponse(charactersResponse.map {
                Log.d("DetailsViewModel", "data: $it")
                it
            })*/
        }
    }
}




