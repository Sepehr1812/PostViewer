package com.pooyan.test.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pooyan.test.data.db.entities.PostEntity

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosts(postEntityList: List<PostEntity>)

    @Query("SELECT * FROM post ORDER BY id DESC LIMIT :count OFFSET :offset")
    suspend fun getPosts(count: Int, offset: Int): List<PostEntity>

    @Query("SELECT * FROM post WHERE id = :postId")
    suspend fun getPost(postId: Int): PostEntity

    @Query("UPDATE post SET likes = :likes WHERE id = :postId")
    suspend fun updateLikes(postId: Int, likes: Int)

    @Query("UPDATE post SET isLiked = :isLiked WHERE id = :postId")
    suspend fun updateIsLiked(postId: Int, isLiked: Boolean)

    @Query("UPDATE post SET comments = :comments WHERE id = :postId")
    suspend fun updateComments(postId: Int, comments: Int)
}