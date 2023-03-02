package com.gateway.marvel.data.utility

import com.gateway.marvel.data.domain.model.Characters

data class MarvelState(
    val marvelData: List<Characters>? = null,
    val error: String? = null,
    val isLoading: Boolean = false
)


