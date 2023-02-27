package com.gateway.marvel.ui.screen.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gateway.marvel.data.domain.model.Data
import com.gateway.marvel.data.repository.MarvelRepoImp
import com.gateway.marvel.data.utility.Resource
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

    var state by mutableStateOf(Data())
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


            state.marvelData?.let {
                if (it.isEmpty() && value.isNotEmpty()) {
                    emptyResponseMessage = "No results were found with the name $value"
                }
            }


            if (query.value.isNotBlank()) {
                searchCharacters()
            }
        }
    }


    private fun searchCharacters() {

        viewModelScope.launch {
            repository.searchMarvel(query.value.trim()).let { response ->
                when (response) {
                    is Resource.Error -> {
                        //TODO: Show a SnackBar or Toast message
                    }
                    is Resource.Loading -> {
                        //TODO: Show a PorgressIndicator
                    }
                    is Resource.Success -> state = state.copy(
                        marvelData = response.data
                    )
                }
            }
        }
    }


}

