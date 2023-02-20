package com.gateway.marvel.data.repository


import android.util.Log
import com.gateway.marvel.data.remote.MarvelAPI
import com.gateway.marvel.repository.RemoteDataSource
import com.gateway.marvel.data.domain.model.*
import com.gateway.marvel.data.utility.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class RemoteDataSourceImp(
    private val api: MarvelAPI
) : RemoteDataSource {


    override suspend fun getCharacters(): Resource<MarvelResponse> {
            return try {

                Log.d("DataSource", api.getCharacters().toString())
                Resource.Success(api.getCharacters())

            } catch (e: HttpException){
                Resource.Error(e.message)
            }
        }


    override suspend fun getComics(): Resource<MarvelResponse> {
        return api.getComics()
    }

    override suspend fun getSeries(): Resource<MarvelResponse> {
        return api.getSeries()
    }

    override suspend fun getStories(): Resource<MarvelResponse>{
        return api.getStories()
    }

    override suspend fun getEvents(): Resource<MarvelResponse> {
        return api.getEvents()
    }

    override suspend fun getCartoons(): Resource<MarvelResponse> {
        return api.getCartoons()
    }

    override suspend fun searchMarvel(name: String): Resource<MarvelData> {
        return api.searchMarvel(query = name)
    }


}