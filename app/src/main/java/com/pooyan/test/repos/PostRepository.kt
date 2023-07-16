package com.pooyan.test.repos

import com.pooyan.test.data.db.dao.PostDao
import com.pooyan.test.data.mappers.PostMapper
import com.pooyan.test.data.models.Post
import javax.inject.Inject

class PostRepository @Inject constructor(private val postDao: PostDao) {

    suspend fun insertPosts(postList: List<Post>) {
        postDao.insertPosts(postList.map { PostMapper.fromDomain(it) })
    }

    suspend fun getPosts(count: Int, offset: Int) =
        postDao.getPosts(count, offset).map {
            PostMapper.toDomain(it)
        }

    suspend fun updateLikes(postId: Int, likes: Int) {
        postDao.updateLikes(postId, likes)
    }

    suspend fun updateComments(postId: Int, comments: Int) {
        postDao.updateComments(postId, comments)
    }
}