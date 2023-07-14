package com.pooyan.test.data.models

data class Comment(
    val id: Int,
    val user: String,
    val content: String,
    val postId: Int
)