package com.gateway.marvel.data.domain.model

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class MarvelResponse(
    @SerialName("data")
    val data:Data? = null
)
