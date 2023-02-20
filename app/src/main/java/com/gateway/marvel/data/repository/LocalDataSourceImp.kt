package com.gateway.marvel.data.repository

import com.gateway.marvel.data.domain.model.*
import com.gateway.marvel.data.local.MarvelDao
import com.gateway.marvel.data.local.MarvelDatabase
import com.gateway.marvel.repository.LocalDataSource

class LocalDataSourceImp(
     private val dao: MarvelDao
) : LocalDataSource {



    override suspend fun getCharacters():MarvelData {
        return dao.getCharacters()
    }

    override suspend fun addCharacters(characters: List<MarvelData>) {
        dao.addCharacters(characters)
    }

    override suspend fun searchCharacters(query: String): List<MarvelData> {
        return dao.searchCharacters(query)
    }

}