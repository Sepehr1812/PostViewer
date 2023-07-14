package com.pooyan.test.data.models

data class Post(
    val id: Int,
    val user: String,
    val caption: String,
    val likes: Int,
    val comments: Int, // comments count
    val image: Int
)
