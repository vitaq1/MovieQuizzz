package com.incite.moviequiz.domain.model

data class FilmPack(
    val id: Int = 0,
    val films: MutableList<Film> = mutableListOf(),
)
