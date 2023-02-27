package com.gateway.marvel.data.repository


import android.content.Context
import com.gateway.marvel.data.domain.model.Characters
import com.gateway.marvel.data.remote.MarvelAPI
import com.gateway.marvel.data.utility.Resource
import com.gateway.marvel.data.utility.checkIfOnline
import com.gateway.marvel.repository.RemoteDataSource
import retrofit2.HttpException
import java.net.SocketTimeoutException

class RemoteDataSourceImp(
    private val api: MarvelAPI,
    private val context:Context
) : RemoteDataSource {


    override suspend fun getCharacters(): Resource<List<Characters>> {
        return handleRemoteResponse(api.getCharacters().data?.marvelData!!)
    }


    override suspend fun getComics(): Resource<List<Characters>> {
        return handleRemoteResponse(api.getComics().data?.marvelData!!)
    }

    override suspend fun getSeries(): Resource<List<Characters>> {
        return handleRemoteResponse(api.getSeries().data?.marvelData!!)
    }

    override suspend fun getStories(): Resource<List<Characters>> {
        return handleRemoteResponse(api.getStories().data?.marvelData!!)
    }

    override suspend fun getEvents(): Resource<List<Characters>> {
        return handleRemoteResponse(api.getEvents().data?.marvelData!!)
    }

    override suspend fun getCartoons(): Resource<List<Characters>> {
        return handleRemoteResponse(api.getCartoons().data?.marvelData!!)
    }

    override suspend fun searchCharacters(name: String): Resource<List<Characters>> {
        return handleRemoteResponse(api.searchMarvel(query = name).data?.marvelData!!)
    }


    private fun <T> handleRemoteResponse(response: T): Resource<T> {
        return try {
            Resource.Loading<T>()

            return if(checkIfOnline(context)) {

                Resource.Success(data = response)
            }else{
                Resource.Error("Internet unavailable")
            }


        } catch (e: HttpException) {
            Resource.Error(e.message)
        } catch (e: SocketTimeoutException) {
            Resource.Error(e.message)
        } catch (e: java.net.UnknownHostException) {
            Resource.Error(e.message)
        } catch (e: Exception) {
            Resource.Error(e.message)
        }
    }
}