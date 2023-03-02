package com.gateway.marvel.repository


import com.gateway.marvel.data.utility.MarvelResult
import com.gateway.marvel.ui.screen.details.MarvelCategories


interface MarvelRepository {

    suspend fun getCharacters(): MarvelResult

    suspend fun searchMarvel(query: String): MarvelResult

    suspend fun getDetailsData(category:MarvelCategories):MarvelResult

}


