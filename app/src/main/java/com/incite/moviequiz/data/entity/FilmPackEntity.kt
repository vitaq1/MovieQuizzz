package com.incite.moviequiz.data.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation
import com.incite.moviequiz.domain.model.Film
import com.incite.moviequiz.domain.model.FilmPack


class FilmPackEntity (
    @Embedded
    val filmPack: PackEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "pack_id"
    )
    val films: List<FilmEntity>
)
{
    fun toFilmPack(): FilmPack {
        return FilmPack(
            id = filmPack.id,
            films = films.map { it.toFilm() }.toMutableList()
        )
    }
}