package com.gateway.marvel.ui.screen.details


import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gateway.marvel.data.domain.model.Characters
import com.gateway.marvel.data.domain.model.Data
import com.gateway.marvel.data.utility.Resource
import com.gateway.marvel.data.utility.checkIfOnline
import com.gateway.marvel.repository.MarvelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel @Inject constructor(
    val context: Context,
    private val repository: MarvelRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {


    var selectedCategory by mutableStateOf("")


    var uiState by mutableStateOf(Data())
        private set



    init {
        val category = savedStateHandle.get<String>("category")

        selectedCategory = category!!

        getData(MarvelCategories.valueOf(selectedCategory))
    }


    private fun getData(category: MarvelCategories) {
        when (category) {
            MarvelCategories.Characters -> getCharacters()
            MarvelCategories.Comics -> getComics()
            MarvelCategories.Series -> getSeries()
            MarvelCategories.Stories -> getStories()
            MarvelCategories.Events -> getEvents()
            MarvelCategories.Cartoons -> getCartoons()
        }
    }


    private fun handleResponse(response: Resource<List<Characters>>) {

        if(!checkIfOnline(context)){
            return
        }
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


    private fun getStories() {

        viewModelScope.launch {
            val repoResponse = repository.getStories()

            handleResponse(repoResponse)
        }
    }


    private fun getSeries() {

        viewModelScope.launch {
            val repoResponse = repository.getSeries()

            handleResponse(repoResponse)
        }
    }

    private fun getEvents() {

        viewModelScope.launch {
            val repoResponse = repository.getEvents()

            handleResponse(repoResponse)
        }
    }

    private fun getCartoons() {

        viewModelScope.launch {
            val repoResponse = repository.getCartoons()

            handleResponse(repoResponse)
        }
    }

    private fun getComics() {

        viewModelScope.launch {
            val repoResponse = repository.getComics()

            handleResponse(repoResponse)
        }
    }

    private fun getCharacters() {

        viewModelScope.launch {
            val repoResponse = repository.getCharacters()

            handleResponse(repoResponse)
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



