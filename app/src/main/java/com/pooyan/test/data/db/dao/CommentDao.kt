package com.pooyan.test.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pooyan.test.data.db.entities.CommentEntity

@Dao
interface CommentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCommentList(commentEntityList: List<CommentEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComment(commentEntity: CommentEntity)

    @Query("SELECT * FROM comment WHERE postId = :postId ORDER BY id DESC LIMIT :count OFFSET :offset")
    suspend fun getComments(postId: Int, count: Int, offset: Int): List<CommentEntity>
}