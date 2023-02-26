package com.gateway.marvel.data.local

import androidx.room.TypeConverter
import com.gateway.marvel.data.domain.Comics
import com.gateway.marvel.data.domain.Events
import com.gateway.marvel.data.domain.Series
import com.gateway.marvel.data.domain.Stories
import com.gateway.marvel.data.domain.model.Thumbnail
import com.google.gson.Gson

class DatabaseConverter {

    @TypeConverter
    fun fromSeries(data: Series): String {
        return Gson().toJson(data)
    }


    @TypeConverter
    fun toSeries(data: String): Series{
        return Gson().fromJson(data, Series::class.java)
    }


    @TypeConverter
    fun fromStories(data: Stories): String {
        return Gson().toJson(data)
    }


    @TypeConverter
    fun toStories(data: String): Stories {
        return Gson().fromJson(data, Stories::class.java)
    }


    @TypeConverter
    fun fromComics(data: Comics): String {
        return Gson().toJson(data)
    }


    @TypeConverter
    fun toComics(data: String): Comics {
        return Gson().fromJson(data, Comics::class.java)
    }


    @TypeConverter
    fun fromEvents(data: Events): String {
        return Gson().toJson(data)
    }


    @TypeConverter
    fun toEvents(data: String): Events{
        return Gson().fromJson(data, Events::class.java)
    }




    @TypeConverter
    fun fromThumbnail(thumbnail: Thumbnail?): String {
        return thumbnail?.path + "." + thumbnail?.extension
    }




    @TypeConverter
    fun toThumbnail(thumbnailString: String?): Thumbnail? {
        if (thumbnailString == null) {
            return null
        }
        val parts = thumbnailString.split(".")
        return Thumbnail(parts[0], parts[1])
    }


}