package com.gateway.marvel.data.local

import androidx.room.TypeConverter
import com.gateway.marvel.data.domain.Comics
import com.gateway.marvel.data.domain.Events
import com.gateway.marvel.data.domain.Series
import com.gateway.marvel.data.domain.Stories
import com.gateway.marvel.data.domain.model.Thumbnail
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DatabaseConverter {

   private val separator = ","

    @TypeConverter
    fun convertListToString(list: List<String>): String {
        val stringBuilder = StringBuilder()
        for (item in list) {
            stringBuilder.append(item).append(separator)
        }
        stringBuilder.setLength(stringBuilder.length - separator.length)
        return stringBuilder.toString()
    }

    private var gson = Gson()

    @TypeConverter
    fun convertStringToList(string: String): List<String> = string.split(separator)



   @TypeConverter
    fun fromSeries(json: String): Series {
        return gson.fromJson<Series>(
            json,
            object : TypeToken<Series>() {}.type
        )
    }



    @TypeConverter
    fun toSeries(data: String): String {
        return gson.toJson(
            data,
            object : TypeToken<Series>() {}.type
        )
    }

    @TypeConverter
    fun fromEvents(json: String): Events {
        return gson.fromJson<Events>(
            json,
            object : TypeToken<Events>() {}.type
        )
    }



    @TypeConverter
    fun toEvents(data: String): String {
        return gson.toJson(
            data,
            object : TypeToken<Events>() {}.type
        )
    }


    @TypeConverter
    fun fromComics(json: String): Comics {
        return gson.fromJson<Comics>(
            json,
            object : TypeToken<Comics>() {}.type
        )
    }



    @TypeConverter
    fun toComics(data: String): String {
        return gson.toJson(
            data,
            object : TypeToken<Comics>() {}.type
        )
    }


    @TypeConverter
    fun fromStories(json: String): Stories {
        return gson.fromJson<Stories>(
            json,
            object : TypeToken<Stories>() {}.type
        )
    }



    @TypeConverter
    fun toStories(data: String): String {
        return gson.toJson(
            data,
            object : TypeToken<Stories>() {}.type
        )
    }




    @TypeConverter
    fun fromThumbnail(thumbnail: Thumbnail?): String? {
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