package com.gateway.marvel.data.repository

import com.gateway.marvel.data.domain.model.Characters
import com.gateway.marvel.data.local.MarvelDatabase
import com.gateway.marvel.data.utility.MarvelResult
import com.gateway.marvel.repository.LocalDataSource
import javax.inject.Inject

class LocalDataSourceImp @Inject constructor(
    db: MarvelDatabase
) : LocalDataSource {


    private val dao = db.marvelDao


    override suspend fun getCharacters(): List<Characters> {
        return dao.getCharacters()
    }

    override suspend fun addCharacters(characters: List<Characters>) {
        dao.addCharacters(characters)
    }

    override suspend fun searchCharacters(query: String): MarvelResult {
        return try {
            MarvelResult.Success(dao.searchCharacters(query))
        } catch (e: Exception) {
            MarvelResult.Error(e.message)
        }
    }
}


