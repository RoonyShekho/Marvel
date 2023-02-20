package com.gateway.marvel.data.domain.use_cases

import com.gateway.marvel.repository.MarvelRepository


import com.gateway.marvel.data.domain.model.*
import com.gateway.marvel.data.utility.Resource
import kotlinx.coroutines.flow.Flow

class GetSeriesUseCase(
    private val repository: MarvelRepository
) {
    suspend operator fun invoke(): Resource<MarvelResponse> =
        repository.getSeries()
}

