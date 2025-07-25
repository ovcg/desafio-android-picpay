package com.picpay.desafio.android.ui.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.picpay.desafio.android.data.common.entity.UserEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    @SerializedName("img") val img: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("id") val id: Int,
    @SerializedName("username") val username: String?
) : Parcelable

fun User.toEntity(): UserEntity {
    return UserEntity(
        img = img,
        name = name,
        id = id.toLong(),
        username = username
    )
}