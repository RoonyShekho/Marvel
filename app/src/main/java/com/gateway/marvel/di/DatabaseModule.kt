package com.gateway.marvel.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.gateway.marvel.data.local.MarvelDatabase
import com.gateway.marvel.data.repository.LocalDataSourceImp
import com.gateway.marvel.repository.LocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {


    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): MarvelDatabase {
        return Room.databaseBuilder(context, MarvelDatabase::class.java, "marvel_db")
            .build()
    }



}