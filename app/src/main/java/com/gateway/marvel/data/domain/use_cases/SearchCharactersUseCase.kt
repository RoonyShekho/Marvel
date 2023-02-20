package com.gateway.marvel.data.domain.use_cases

import com.gateway.marvel.data.domain.model.MarvelData
import com.gateway.marvel.data.utility.Resource
import com.gateway.marvel.repository.MarvelRepository
import kotlinx.coroutines.flow.Flow

class SearchCharactersUseCase(
    private val repository:MarvelRepository
) {
   /* suspend operator fun invoke(query:String): Flow<Resource<List<MarvelData>>> =
        repository.searchCharacters(query)*/
}