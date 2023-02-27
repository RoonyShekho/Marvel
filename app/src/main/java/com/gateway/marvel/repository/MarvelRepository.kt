package com.gateway.marvel.repository

import com.gateway.marvel.data.domain.model.Characters
import com.gateway.marvel.data.utility.Resource
import com.gateway.marvel.ui.screen.details.MarvelCategories


interface MarvelRepository {

    suspend fun getCharacters(): Resource<List<Characters>>

    suspend fun searchMarvel(query: String): Resource<List<Characters>>

    suspend fun getDetailsData(category:MarvelCategories):Resource<List<Characters>>

}


