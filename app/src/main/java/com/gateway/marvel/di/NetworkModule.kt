package com.gateway.marvel.di

import android.content.Context
import com.gateway.marvel.data.remote.MarvelAPI
import com.gateway.marvel.data.repository.RemoteDataSourceImp
import com.gateway.marvel.data.utility.Constants
import com.gateway.marvel.repository.RemoteDataSource
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@ExperimentalSerializationApi
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Singleton
    @Provides
    fun provideRetrofitInstance(okHttp: OkHttpClient): Retrofit {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType))
            .client(okHttp)
            .build()
    }


    private val json = Json {
        ignoreUnknownKeys = true
    }



    private val httpLoggingInterceptor: HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }


    @Singleton
    @Provides
    fun provideMarvelAPI(
        retrofit: Retrofit
    ): MarvelAPI {
        return retrofit.create(MarvelAPI::class.java)
    }


    @Singleton
    @Provides
    fun provideRemoteDataSource(
        api:MarvelAPI,
        context:Context
    ):RemoteDataSource{
        return RemoteDataSourceImp(api, context)
    }




}