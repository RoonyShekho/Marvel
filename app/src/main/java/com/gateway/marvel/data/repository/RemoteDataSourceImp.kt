package com.gateway.marvel.data.repository


import com.gateway.marvel.data.remote.MarvelAPI
import com.gateway.marvel.data.utility.MarvelResult
import com.gateway.marvel.repository.RemoteDataSource

class RemoteDataSourceImp(
    private val api: MarvelAPI
) : RemoteDataSource {


    override suspend fun getCharacters(): MarvelResult {
        return try {
            MarvelResult.Loading

            val response = api.getCharacters()
            MarvelResult.Success(data = response.data?.marvelData!!)

        }catch(e:Exception){
            MarvelResult.Error(e.message)
        }
    }


    override suspend fun getComics(): MarvelResult {
        return try {
            MarvelResult.Loading

            val response = api.getComics()
            MarvelResult.Success(data = response.data?.marvelData!!)

        }catch(e:Exception){
            MarvelResult.Error(e.message)
        }
    }

    override suspend fun getSeries():MarvelResult {
        return try {
            MarvelResult.Loading

            val response = api.getSeries().data?.marvelData!!
            MarvelResult.Success(data = response)

        }catch(e:Exception){
            MarvelResult.Error(e.message)
        }
    }

    override suspend fun getStories(): MarvelResult{
        return try {
            MarvelResult.Loading

            val response = api.getStories().data?.marvelData!!
            MarvelResult.Success(data = response)

        }catch(e:Exception){
            MarvelResult.Error(e.message)
        }
    }

    override suspend fun getEvents(): MarvelResult {
        return try {
            MarvelResult.Loading

            val response = api.getEvents().data?.marvelData!!
            MarvelResult.Success(data = response)

        }catch(e:Exception){
            MarvelResult.Error(e.message)
        }
    }

    override suspend fun getCartoons(): MarvelResult {
        return try {
            MarvelResult.Loading

            val response = api.getCartoons().data?.marvelData!!
            MarvelResult.Success(data = response)

        }catch(e:Exception){
            MarvelResult.Error(e.message)
        }
    }

    override suspend fun searchCharacters(name: String): MarvelResult {
        return try {
            MarvelResult.Loading

            val response = api.getCharacters().data?.marvelData!!
            MarvelResult.Success(data = response)

        }catch(e:Exception){
            MarvelResult.Error(e.message)
        }
    }

}