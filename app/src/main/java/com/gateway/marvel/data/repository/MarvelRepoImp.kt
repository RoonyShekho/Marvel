package com.gateway.marvel.data.repository


import android.content.Context
import com.gateway.marvel.data.utility.MarvelResult
import com.gateway.marvel.data.utility.checkIfOnline
import com.gateway.marvel.repository.LocalDataSource
import com.gateway.marvel.repository.MarvelRepository
import com.gateway.marvel.repository.RemoteDataSource
import com.gateway.marvel.ui.screen.details.MarvelCategories
import javax.inject.Inject

class MarvelRepoImp @Inject constructor(
    private val local: LocalDataSource,
    private val remote: RemoteDataSource,
    private val context: Context
) : MarvelRepository {


    override suspend fun searchMarvel(query: String): MarvelResult{
        val cacheFromDatabase = local.getCharacters().isNotEmpty() || !checkIfOnline(context)

        return if (cacheFromDatabase) {
            local.searchCharacters(query)
        } else {
            remote.searchCharacters(query)
        }
    }

    override suspend fun getCharacters():MarvelResult{
        return remote.getCharacters()
    }


    override suspend fun getDetailsData(category: MarvelCategories): MarvelResult {
        return when (category) {
            MarvelCategories.Characters -> remote.getCharacters()
            MarvelCategories.Comics -> remote.getComics()
            MarvelCategories.Series -> remote.getSeries()
            MarvelCategories.Stories -> remote.getStories()
            MarvelCategories.Events -> remote.getEvents()
            MarvelCategories.Cartoons -> remote.getCartoons()
        }
    }


}



