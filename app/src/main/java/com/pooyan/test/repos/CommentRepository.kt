package com.pooyan.test.repos

import com.pooyan.test.data.db.dao.CommentDao
import com.pooyan.test.data.mappers.CommentMapper
import com.pooyan.test.data.models.Comment
import javax.inject.Inject

class CommentRepository @Inject constructor(private val commentDao: CommentDao) {

    suspend fun insertComment(comment: Comment) {
        commentDao.insertComment(CommentMapper.fromDomain(comment))
    }

    suspend fun getComments(postId: Int, count: Int) =
        commentDao.getComments(postId, count).map {
            CommentMapper.toDomain(it)
        }

}