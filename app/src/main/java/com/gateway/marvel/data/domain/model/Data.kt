package com.gateway.marvel.data.domain.model

import kotlinx.serialization.SerialName


@kotlinx.serialization.Serializable
data class Data(
    @SerialName("results")
    val marvelData:List<MarvelData>? = null,
)
