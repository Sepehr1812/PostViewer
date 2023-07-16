package com.pooyan.test.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "post")
data class PostEntity(
    val user: String,
    val caption: String,
    val likes: Int,
    val comments: Int, // comments count
    val image: Int,
    val isLiked: Boolean,

    @PrimaryKey
    val id: Int
)
