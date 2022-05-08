package com.incite.moviequiz.domain.repository

import com.incite.moviequiz.domain.model.Film
import com.incite.moviequiz.domain.model.FilmPack
import com.incite.moviequiz.domain.model.Player

interface MovieRepository {

    fun getFilmPacks(): List<FilmPack>

    suspend fun getFilmPackById(id: Int): FilmPack

    fun getFilms(): List<Film>

    fun getFilmById(id: Int): Film

    fun addFilm(film: Film)

    fun addFilmPack(filmPack: FilmPack)

    fun getPlayer(): Player

    fun updatePlayer(player: Player)

    fun updateFilm(film: Film)


}