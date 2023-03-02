package com.gateway.marvel.repository

import com.gateway.marvel.data.domain.model.Characters
import com.gateway.marvel.data.utility.MarvelResult


interface LocalDataSource {


    suspend fun getCharacters(): List<Characters>


    suspend fun addCharacters(characters: List<Characters>)

    suspend fun searchCharacters(query:String): MarvelResult


}