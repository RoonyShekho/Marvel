package com.gateway.marvel.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gateway.marvel.data.domain.model.*


@Database(
    entities = [Characters::class],
    version = 1
)
@TypeConverters(DatabaseConverter::class)
abstract class MarvelDatabase : RoomDatabase() {
    abstract val marvelDao: MarvelDao
}