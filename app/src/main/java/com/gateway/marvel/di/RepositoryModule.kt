package com.gateway.marvel.di

import android.app.Application
import android.content.Context
import com.gateway.marvel.data.local.MarvelDatabase
import com.gateway.marvel.data.repository.LocalDataSourceImp
import com.gateway.marvel.data.repository.MarvelRepoImp
import com.gateway.marvel.repository.LocalDataSource
import com.gateway.marvel.repository.MarvelRepository
import com.gateway.marvel.repository.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(
        local: LocalDataSource,
        remote: RemoteDataSource,
        context:Context
    ): MarvelRepository {
        return MarvelRepoImp(local = local, remote = remote, context = context)
    }



    @Singleton
    @Provides
    fun provideContext(application: Application):Context = application.applicationContext

    @Provides
    @Singleton
    fun localDataSource(
        db: MarvelDatabase
    ): LocalDataSource {
        return LocalDataSourceImp(db)
    }


}