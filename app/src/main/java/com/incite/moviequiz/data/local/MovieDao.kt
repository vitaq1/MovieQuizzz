package com.incite.moviequiz.data.local

import androidx.room.*
import com.incite.moviequiz.data.entity.FilmEntity
import com.incite.moviequiz.data.entity.FilmPackEntity
import com.incite.moviequiz.data.entity.PackEntity
import com.incite.moviequiz.data.entity.PlayerEntity


@Dao
interface MovieDao {

    @Transaction
    @Query("SELECT * FROM packentity")
    suspend fun getFilmPacks(): List<FilmPackEntity>

    @Transaction
    @Query("SELECT * FROM packentity WHERE id = :id")
    suspend fun getFilmPackById(id: Int): FilmPackEntity

    @Query("SELECT * FROM filmentity")
    suspend fun getFilms(): List<FilmEntity>

    @Query("SELECT * FROM filmentity WHERE id = :id")
    suspend fun getFilmById(id: Int): FilmEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateFilm(film: FilmEntity)

    @Insert
    suspend fun addFilmPack(pack: PackEntity)

    @Insert
    suspend fun addFilm(film: FilmEntity)

    @Query("SELECT * FROM playerentity")
    suspend fun getPlayer(): PlayerEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updatePlayer(playerEntity: PlayerEntity)

}