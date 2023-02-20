package com.gateway.marvel.data.domain.use_cases

data class UseCase(
    val getCartoonsUseCase: GetCartoonsUseCase,
    val getCharactersUseCase: GetCharactersUseCase,
    val getSeriesUseCase: GetSeriesUseCase,
    val getStoriesUseCase: GetStoriesUseCase,
    val getEventsUseCase: GetEventsUseCase,
    val getComicsUseCase: GetComicsUseCase,
    val searchCharactersUseCase: SearchCharactersUseCase
)
