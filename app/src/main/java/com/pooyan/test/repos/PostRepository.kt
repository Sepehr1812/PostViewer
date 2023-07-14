package com.pooyan.test.repos

import com.pooyan.test.data.db.dao.PostDao
import com.pooyan.test.data.mappers.PostMapper
import com.pooyan.test.data.models.Post
import javax.inject.Inject

class PostRepository @Inject constructor(private val postDao: PostDao) {

    suspend fun insertPost(post: Post) {
        postDao.insertPost(PostMapper.fromDomain(post))
    }

    suspend fun getPosts(count: Int) =
        postDao.getPosts(count).map {
            PostMapper.toDomain(it)
        }

    suspend fun updateLikes(postId: Int, likes: Int) {
        postDao.updateLikes(postId, likes)
    }

    suspend fun updateComments(postId: Int, comments: Int) {
        postDao.updateComments(postId, comments)
    }
}