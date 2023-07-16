package com.pooyan.test.usecases

import com.pooyan.test.data.models.Post
import com.pooyan.test.repos.PostRepository
import javax.inject.Inject

class GetPost @Inject constructor(private val postRepository: PostRepository) :
    BaseUseCase<GetPost.RequestValue, Post>() {

    data class RequestValue(val postId: Int) : BaseUseCase.RequestValue

    override suspend fun executeUseCase(requestValues: RequestValue) =
        postRepository.getPost(requestValues.postId)
}