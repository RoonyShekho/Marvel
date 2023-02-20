package com.gateway.marvel.data.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gateway.marvel.data.domain.Comics
import com.gateway.marvel.data.domain.Events
import com.gateway.marvel.data.domain.Series
import com.gateway.marvel.data.domain.Stories
import kotlinx.serialization.Serializable


@Serializable
@Entity(tableName = "marvel_table")
data class MarvelData(
    @PrimaryKey var id:Int? = null,
    val title:String? = null,
    val description:String? = null,
    val thumbnail: Thumbnail? = null,
    val series: Series,
    val stories: Stories,
    val comics: Comics,
    val events: Events,
)





