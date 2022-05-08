package com.incite.moviequiz.data.entity


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.incite.moviequiz.domain.model.Film

@Entity
data class FilmEntity(

    @PrimaryKey val id: Int = 0,
    val pack_id: Int = 0,
    var name: String = "",
    var image: String = "",
    var completed: Boolean = false
) {
    fun toFilm(): Film {
        return Film(
            id = id,
            pack_id = pack_id,
            name = name,
            image = image,
            completed = completed
        )
    }
}