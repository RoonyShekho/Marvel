package com.gateway.marvel.data.repository


import android.content.Context
import android.util.Log
import com.gateway.marvel.data.domain.model.Characters
import com.gateway.marvel.data.utility.Resource
import com.gateway.marvel.data.utility.checkIfOnline
import com.gateway.marvel.repository.LocalDataSource
import com.gateway.marvel.repository.MarvelRepository
import com.gateway.marvel.repository.RemoteDataSource
import com.gateway.marvel.ui.screen.details.MarvelCategories
import retrofit2.HttpException
import java.net.SocketTimeoutException
import javax.inject.Inject

class MarvelRepoImp @Inject constructor(
    private val local: LocalDataSource,
    private val remote: RemoteDataSource,
    private val context:Context
) : MarvelRepository {


    override suspend fun searchMarvel(query: String): Resource<List<Characters>> {
        val cacheFromDatabase = local.getCharacters().isNotEmpty() || !checkIfOnline(context)

        return if (cacheFromDatabase) {
            local.searchCharacters(query)
        }else{
            remote.searchCharacters(query)
        }
    }

    override suspend fun getCharacters(): Resource<List<Characters>> {
        val response = remote.getCharacters()
        local.addCharacters(response.data!!)
        return handleRemoteResponse (response.data, context)
    }


    override suspend fun getDetailsData(category:MarvelCategories):Resource<List<Characters>>{
        return when(category){
            MarvelCategories.Characters -> getCharacters()
            MarvelCategories.Comics -> remote.getComics()
            MarvelCategories.Series -> remote.getSeries()
            MarvelCategories.Stories -> remote.getStories()
            MarvelCategories.Events -> remote.getEvents()
            MarvelCategories.Cartoons -> remote.getCartoons()
        }
    }


}



private fun <T> handleRemoteResponse(response: T, context: Context): Resource<T> {
    return try {
        Resource.Loading<T>()

        return if(checkIfOnline(context)) {
            Resource.Success(data = response)
        }else{
            Log.d("Repository", "Internet unavailable")
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


