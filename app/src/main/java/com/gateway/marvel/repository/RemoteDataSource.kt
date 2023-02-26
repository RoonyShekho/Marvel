package com.gateway.marvel.repository


import com.gateway.marvel.data.domain.model.Characters
import com.gateway.marvel.data.utility.Resource


interface RemoteDataSource{

    suspend fun getCharacters():Resource<List<Characters>>

    suspend fun getComics(): Resource<List<Characters>>

    suspend fun getSeries():Resource<List<Characters>>

    suspend fun getStories(): Resource<List<Characters>>

    suspend fun getEvents(): Resource<List<Characters>>

    suspend fun getCartoons(): Resource<List<Characters>>

    suspend fun searchCharacters(name:String):Resource<List<Characters>>


}