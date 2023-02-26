package com.gateway.marvel.data.domain

import com.gateway.marvel.data.domain.model.Item
import kotlinx.serialization.Serializable

@Serializable
data class Events(
    val items: List<Item> = emptyList()
)