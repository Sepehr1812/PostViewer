package com.pooyan.test.usecases

import com.pooyan.test.repos.PostRepository
import javax.inject.Inject

class UpdateComments @Inject constructor(private val postRepository: PostRepository) :
    BaseUseCase<UpdateComments.RequestValue, Unit>() {

    data class RequestValue(val postId: Int, val comments: Int) : BaseUseCase.RequestValue

    override suspend fun executeUseCase(requestValues: RequestValue) {
        requestValues.apply {
            postRepository.updateComments(postId, comments)
        }
    }
}