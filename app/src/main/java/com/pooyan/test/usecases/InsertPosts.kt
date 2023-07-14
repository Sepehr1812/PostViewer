package com.pooyan.test.usecases

import com.pooyan.test.data.models.Post
import com.pooyan.test.repos.PostRepository
import javax.inject.Inject

class InsertPosts @Inject constructor(private val postRepository: PostRepository) :
    BaseUseCase<InsertPosts.RequestValue, Unit>() {

    data class RequestValue(val postList: List<Post>) : BaseUseCase.RequestValue

    override suspend fun executeUseCase(requestValues: RequestValue) {
        postRepository.insertPosts(requestValues.postList)
    }
}