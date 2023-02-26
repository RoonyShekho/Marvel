package com.gateway.marvel.data.repository


import android.content.Context
import android.util.Log
import com.gateway.marvel.data.domain.model.Characters
import com.gateway.marvel.data.utility.Resource
import com.gateway.marvel.data.utility.checkIfOnline
import com.gateway.marvel.repository.LocalDataSource
import com.gateway.marvel.repository.MarvelRepository
import com.gateway.marvel.repository.RemoteDataSource
import javax.inject.Inject

class MarvelRepoImp @Inject constructor(
    private val local: LocalDataSource,
    private val remote: RemoteDataSource,
    private val context:Context
) : MarvelRepository {


    override suspend fun searchMarvel(query: String): Resource<List<Characters>> {
        val cacheFromDatabase = local.getCharacters().isNotEmpty() || !checkIfOnline(context)

        Log.d("Repository", cacheFromDatabase.toString())

        return local.searchCharacters(query)
    }


    override suspend fun getCharacters(): Resource<List<Characters>> {
        val response = remote.getCharacters()
        local.addCharacters(response.data!!)
        return returnIfOnline(response, context)
    }


    override suspend fun getComics(): Resource<List<Characters>> {
        return returnIfOnline(remote.getComics(), context)

    }

    override suspend fun getSeries(): Resource<List<Characters>> {
        return returnIfOnline(remote.getSeries(), context)
    }
    override suspend fun getStories(): Resource<List<Characters>> {
        return returnIfOnline(remote.getStories(), context)
    }

    override suspend fun getEvents(): Resource<List<Characters>> {
        return returnIfOnline(remote.getEvents(), context)
    }

    override suspend fun getCartoons(): Resource<List<Characters>> {
        return returnIfOnline(remote.getCartoons(), context)
    }

    override suspend fun insertCharacters(characters: List<Characters>) {
        local.addCharacters(characters)
    }

}


private fun returnIfOnline(response:Resource<List<Characters>>, context:Context):Resource<List<Characters>>{
    return if(checkIfOnline(context)){
        response
    }else{
        Resource.Error("No internet connection")
    }
}


