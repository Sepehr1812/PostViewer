package com.pooyan.test.repos

import com.pooyan.test.data.db.dao.CommentDao
import com.pooyan.test.data.mappers.CommentMapper
import com.pooyan.test.data.models.Comment
import javax.inject.Inject

class CommentRepository @Inject constructor(private val commentDao: CommentDao) {

    suspend fun insertCommentList(commentList: List<Comment>) {
        commentDao.insertCommentList(commentList.map { CommentMapper.fromDomain(it) })
    }

    suspend fun insertComment(comment: Comment) {
        commentDao.insertComment(CommentMapper.fromDomain(comment))
    }

    suspend fun getComments(postId: Int, count: Int, offset: Int) =
        commentDao.getComments(postId, count, offset).map {
            CommentMapper.toDomain(it)
        }
}