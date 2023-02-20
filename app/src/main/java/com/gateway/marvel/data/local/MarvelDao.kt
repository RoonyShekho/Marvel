package com.gateway.marvel.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gateway.marvel.data.domain.model.*

@Dao
interface MarvelDao {

    @Query("SELECT * FROM marvel_table ORDER BY id ASC")
    fun getCharacters():MarvelData

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCharacters(characters: List<MarvelData>)


    @Query(  """
            SELECT * 
            FROM marvel_table
            WHERE LOWER(title) LIKE '%' || LOWER(:query)
     
        """)
    suspend fun searchCharacters(query:String):List<MarvelData>


}