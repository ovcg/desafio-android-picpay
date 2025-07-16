package com.picpay.desafio.android.data.common.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.picpay.desafio.android.ui.model.User

@Entity(tableName = "Users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val username: String?,
    val name: String?,
    val img: String?
)

fun List<UserEntity>.toModel(): List<User> {
    return this.map {
        User(
            id = it.id.toInt(),
            username = it.username,
            name = it.name,
            img = it.img
        )
    }
}