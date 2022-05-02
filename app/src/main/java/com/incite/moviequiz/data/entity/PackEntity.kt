package com.incite.moviequiz.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.incite.moviequiz.domain.model.Film

@Entity
data class PackEntity(

    @PrimaryKey
    val id: Int = 0,
) {
    /*fun toFilm(): Film {
        return Film(
            id = id,
            name = name,
            image = image,
        )
    }*/
}