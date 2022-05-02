package com.incite.moviequiz.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.incite.moviequiz.data.entity.FilmEntity
import com.incite.moviequiz.data.entity.FilmPackEntity
import com.incite.moviequiz.data.entity.PackEntity
import com.incite.moviequiz.data.entity.PlayerEntity


@Database(
    entities = [FilmEntity::class, PackEntity::class, PlayerEntity::class],
    version = 10
)
abstract class MovieDatabase: RoomDatabase() {

        abstract val dao: MovieDao
}