package com.gateway.marvel.data.repository


import android.content.Context
import android.util.Log
import com.gateway.marvel.data.domain.model.MarvelData
import com.gateway.marvel.data.domain.model.MarvelResponse
import com.gateway.marvel.data.utility.Resource
import com.gateway.marvel.data.utility.checkIfOnline
import com.gateway.marvel.repository.LocalDataSource
import com.gateway.marvel.repository.MarvelRepository
import com.gateway.marvel.repository.RemoteDataSource
import javax.inject.Inject

class MarvelRepoImp @Inject constructor(
    val local: LocalDataSource,
    val remote: RemoteDataSource,
    val context: Context
) : MarvelRepository {


    override suspend fun searchMarvel(query: String):Resource<MarvelData>{
        if(checkIfOnline(context)){
            return Resource.Success(remote.searchMarvel(query).data)
        }

        return Resource.Success(local.searchCharacters(query))
    }



    override suspend fun getCharacters(): Resource<MarvelResponse> {

            Log.d("Repository", remote.getCharacters().let{
                it.data
            }.toString())

        return remote.getCharacters()
        }


    override suspend fun getComics(): Resource<MarvelResponse> {
        return remote.getComics()
    }

    override suspend fun getSeries(): Resource<MarvelResponse> {
        return remote.getSeries()
    }

    override suspend fun getStories(): Resource<MarvelResponse> {
        return remote.getComics()
    }

    override suspend fun getEvents():Resource<MarvelResponse> {
        return remote.getComics()
    }

    override suspend fun getCartoons():Resource<MarvelResponse> {
        return remote.getComics()
    }

    override suspend fun insertCharacters(characters: List<MarvelData>) {
        local.addCharacters(characters)
    }


}



