package com.gateway.marvel.ui.screen.search

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gateway.marvel.data.repository.MarvelRepoImp
import com.gateway.marvel.data.utility.MarvelResult
import com.gateway.marvel.data.utility.MarvelState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: MarvelRepoImp
) : ViewModel() {


    private val _query = mutableStateOf("")
    val query = _query


    var uiState by mutableStateOf(MarvelState())
        private set

    var isLoading by mutableStateOf(false)
        private set


    var emptyResponseMessage by mutableStateOf("")
        private set

    private var job: Job? = null

    fun setQuery(value: String) {
        _query.value = value

        // wait some time until making
        // the response
        job?.cancel()
        job = viewModelScope.launch {

            delay(700L)

            isLoading = true

            uiState.marvelData?.let {
                isLoading = false
                if (it.isEmpty() && value.isNotEmpty()) {
                    emptyResponseMessage = "No results were found with the name $query"
                }
            }


            if (_query.value.isNotBlank()) {
                searchCharacters()
            }
        }
    }


    private fun searchCharacters() {

        isLoading = true
        viewModelScope.launch {
            repository.searchMarvel(_query.value.trim()).let { response ->
                when (response) {
                    is MarvelResult.Error -> {
                        Log.d("Search", response.message!!)
                        isLoading = false
                    }
                    MarvelResult.Loading -> {
                        uiState = uiState.copy(
                            isLoading = true
                        )
                    }
                    is MarvelResult.Success -> {
                        uiState = uiState.copy(
                            marvelData = response.data
                        )
                        isLoading = false
                    }
                }
            }
        }
    }


}

