package com.gateway.marvel.repository


import com.gateway.marvel.data.utility.MarvelResult


interface RemoteDataSource{

    suspend fun getCharacters(): MarvelResult

    suspend fun getComics(): MarvelResult

    suspend fun getSeries():MarvelResult

    suspend fun getStories(): MarvelResult

    suspend fun getEvents(): MarvelResult

    suspend fun getCartoons(): MarvelResult

    suspend fun searchCharacters(name:String):MarvelResult


}