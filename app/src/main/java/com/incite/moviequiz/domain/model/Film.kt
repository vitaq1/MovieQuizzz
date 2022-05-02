package com.incite.moviequiz.domain.model

import com.incite.moviequiz.data.entity.FilmEntity

data class Film(

    val id: Int,
    val pack_id: Int = 0,
    var name: String = "",
    var image: String = "",
    var completed: Boolean = false
) {
    fun toFilmEntity(): FilmEntity {
        return FilmEntity(
            id = id,
            pack_id = pack_id,
            name = name,
            image = image,
            completed = completed,
        )
    }

}
