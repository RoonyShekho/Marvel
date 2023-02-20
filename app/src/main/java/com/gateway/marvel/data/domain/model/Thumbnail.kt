package com.gateway.marvel.data.domain.model


@kotlinx.serialization.Serializable
data class Thumbnail(
    val extension: String = "",
    val path: String = ""
)