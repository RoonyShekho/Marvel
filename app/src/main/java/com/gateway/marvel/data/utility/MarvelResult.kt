package com.gateway.marvel.data.utility

import com.gateway.marvel.data.domain.model.Characters


sealed class MarvelResult(
    val message: String? = null,
    val data: List<Characters>? = null
) {

    class Success(data: List<Characters>) :
        MarvelResult(data = data)

    class Error(errorMessage: String?) :
        MarvelResult(message = errorMessage)

    object Loading : MarvelResult()
}