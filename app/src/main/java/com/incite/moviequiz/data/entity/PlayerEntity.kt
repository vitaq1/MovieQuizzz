package com.incite.moviequiz.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.incite.moviequiz.domain.model.Player

@Entity
data class PlayerEntity(

    @PrimaryKey val id: Int = 0,
    var tf_record: Int = 0,
    var guess_record: Int = 0,
    var money: Int = 100,


) {
    fun toPlayer(): Player {
        return Player(
            id = id,
            tf_record = tf_record,
            guess_record = guess_record,
            money = money,
        )
    }
}