package com.gateway.marvel.data.domain

import com.gateway.marvel.data.domain.model.MarvelData
import kotlinx.serialization.Serializable

@Serializable
data class Stories(
    val items: List<MarvelData>
)