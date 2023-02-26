package com.gateway.marvel.data.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gateway.marvel.data.domain.Comics
import com.gateway.marvel.data.domain.Events
import com.gateway.marvel.data.domain.Series
import com.gateway.marvel.data.domain.Stories
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
@Entity(tableName = "marvel_table")
data class Characters(
    @PrimaryKey var id:Int? = null,
    @SerialName("name")
    val name:String = "",
    val description:String? = null,
    val thumbnail: Thumbnail? = null,
    val series: Series = Series(),
    val stories: Stories = Stories(),
    val comics: Comics = Comics(),
    val events: Events = Events()
)





