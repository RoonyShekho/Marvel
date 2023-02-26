package com.gateway.marvel.data.remote


import com.gateway.marvel.data.domain.model.MarvelResponse
import com.gateway.marvel.data.utility.Constants.API_KEY
import com.gateway.marvel.data.utility.Constants.GET_PATH
import com.gateway.marvel.data.utility.Constants.HASH
import com.gateway.marvel.data.utility.Constants.TIMESTAMP
import retrofit2.http.GET
import retrofit2.http.Query


interface MarvelAPI {

    @GET("$GET_PATH/characters")
    suspend fun getCharacters(
        @Query("apikey") apikey: String = API_KEY,
        @Query("ts") timestamp: String = TIMESTAMP,
        @Query("hash") hash: String = HASH
    ): MarvelResponse


    @GET("$GET_PATH/cartoons")
    suspend fun getCartoons(
        @Query("apikey") apikey: String = API_KEY,
        @Query("ts") timestamp: String = TIMESTAMP,
        @Query("hash") hash: String = HASH
    ): MarvelResponse


    @GET("$GET_PATH/events")
    suspend fun getEvents(
        @Query("apikey") apikey: String = API_KEY,
        @Query("ts") timestamp: String = TIMESTAMP,
        @Query("hash") hash: String = HASH
    ): MarvelResponse

    @GET("$GET_PATH/series")
    suspend fun getSeries(
        @Query("apikey") apikey: String = API_KEY,
        @Query("ts") timestamp: String = TIMESTAMP,
        @Query("hash") hash: String = HASH
    ): MarvelResponse


    @GET("$GET_PATH/stories")
    suspend fun getStories(
        @Query("apikey") apikey: String = API_KEY,
        @Query("ts") timestamp: String = TIMESTAMP,
        @Query("hash") hash: String = HASH
    ):MarvelResponse

    @GET("$GET_PATH/comics")
    suspend fun getComics(
        @Query("apikey") apikey: String = API_KEY,
        @Query("ts") timestamp: String = TIMESTAMP,
        @Query("hash") hash: String = HASH
    ): MarvelResponse


    @GET("$GET_PATH/characters")
    suspend fun searchMarvel(
        @Query("apikey") apiKey: String = API_KEY,
        @Query("ts") timestamp: String = TIMESTAMP,
        @Query("hash") hash: String = HASH,
        @Query("name") query: String
    ): MarvelResponse


}

