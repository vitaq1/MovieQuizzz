package com.incite.moviequiz.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PackEntity(

    @PrimaryKey
    val id: Int = 0,
) {
}