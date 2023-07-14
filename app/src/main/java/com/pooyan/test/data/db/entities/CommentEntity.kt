package com.pooyan.test.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comment")
data class CommentEntity(
    val user: String,
    val content: String,
    val postId: Int,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)