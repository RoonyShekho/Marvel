package com.gateway.marvel.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gateway.marvel.data.domain.model.*

@Dao
interface MarvelDao {

    @Query("SELECT * FROM marvel_table ORDER BY id ASC")
    suspend fun getCharacters():List<Characters>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCharacters(characters: List<Characters>)


    @Query(  """
            SELECT * 
            FROM marvel_table
            WHERE name LIKE '%' || :query || '%'
        """)
    suspend fun searchCharacters(query:String):List<Characters>


}