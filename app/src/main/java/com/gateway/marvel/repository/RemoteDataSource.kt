package com.gateway.marvel.repository


import com.gateway.marvel.data.domain.model.*
import com.gateway.marvel.data.utility.Resource
import kotlinx.coroutines.flow.Flow


interface RemoteDataSource{

    suspend fun getCharacters():Resource<MarvelResponse>

    suspend fun getComics(): Resource<MarvelResponse>

    suspend fun getSeries(): Resource<MarvelResponse>

    suspend fun getStories(): Resource<MarvelResponse>

    suspend fun getEvents(): Resource<MarvelResponse>

    suspend fun getCartoons(): Resource<MarvelResponse>

    suspend fun searchMarvel(name:String):Resource<MarvelData>

}