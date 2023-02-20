package com.gateway.marvel.repository

import com.gateway.marvel.data.domain.model.*


interface LocalDataSource {


    suspend fun getCharacters(): MarvelResponse


    suspend fun addCharacters(characters: List<MarvelData>)

    suspend fun searchCharacters(query:String):MarvelData


}