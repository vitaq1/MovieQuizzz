package com.incite.moviequiz.data.repository

import com.incite.moviequiz.data.entity.FilmEntity
import com.incite.moviequiz.data.entity.PackEntity
import com.incite.moviequiz.data.entity.PlayerEntity
import com.incite.moviequiz.data.local.MovieDao
import com.incite.moviequiz.domain.model.Film
import com.incite.moviequiz.domain.model.FilmPack
import com.incite.moviequiz.domain.model.Player
import com.incite.moviequiz.domain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val dao: MovieDao
) : MovieRepository {

    override fun getFilmPacks(): List<FilmPack> = runBlocking {
        withContext(Dispatchers.IO) {
            dao.getFilmPacks().map { it.toFilmPack() }
        }

    }

    override suspend fun getFilmPackById(id: Int): FilmPack {
        return dao.getFilmPackById(id).toFilmPack()
    }

    override fun getFilms(): List<Film> = runBlocking {
        withContext(Dispatchers.IO) {
            dao.getFilms().map { it.toFilm() }
        }
    }

    override fun getFilmById(id: Int): Film = runBlocking {
        withContext(Dispatchers.IO) {
            dao.getFilmById(id).toFilm()
        }
    }

    override fun addFilm(film: Film) = runBlocking {
        withContext(Dispatchers.IO) {
            dao.addFilm(film.toFilmEntity())
        }}

    override fun addFilmPack(filmPack: FilmPack)  = runBlocking {
        withContext(Dispatchers.IO) {
            dao.addFilmPack(PackEntity(filmPack.id))
        }
    }

    override fun getPlayer() = runBlocking {
        withContext(Dispatchers.IO) {
            if (dao.getPlayer() == null) dao.updatePlayer(PlayerEntity())
            dao.getPlayer().toPlayer()
        }
    }

    override fun updatePlayer(player: Player) = runBlocking {
        withContext(Dispatchers.IO) {
            dao.updatePlayer(player.toPlayerEntity())
        }
    }

    override fun updateFilm(film: Film) = runBlocking {
        withContext(Dispatchers.IO) {
            dao.updateFilm(film.toFilmEntity())
        }
    }

}