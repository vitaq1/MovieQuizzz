package com.incite.moviequiz.domain.model

import com.incite.moviequiz.data.entity.PlayerEntity

data class Player(
    val id: Int = 0,
    var tf_record: Int = 0,
    var guess_record: Int = 0,
    var money: Int = 100,
) {
    fun toPlayerEntity(): PlayerEntity {
        return PlayerEntity(
            id = id,
            tf_record = tf_record,
            guess_record = guess_record,
            money = money,
        )
    }
}