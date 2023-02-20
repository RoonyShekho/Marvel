package com.gateway.marvel.repository

import com.gateway.marvel.data.domain.model.*
import com.gateway.marvel.data.utility.Resource
import kotlinx.coroutines.flow.Flow


interface MarvelRepository {

    suspend fun getCharacters(): Resource<MarvelResponse>

    suspend fun searchMarvel(query: String): Resource<MarvelData>

    suspend fun getComics(): Resource<MarvelResponse>

    suspend fun getSeries(): Resource<MarvelResponse>

    suspend fun getStories(): Resource<MarvelResponse>

    suspend fun getEvents(): Resource<MarvelResponse>

    suspend fun getCartoons(): Resource<MarvelResponse>


    suspend fun insertCharacters(characters: List<MarvelData>)


}


