package com.pooyan.test.usecases

import com.pooyan.test.repos.PostRepository
import javax.inject.Inject

class UpdateIsLiked @Inject constructor(private val postRepository: PostRepository) :
    BaseUseCase<UpdateIsLiked.RequestValue, Unit>() {

    data class RequestValue(val postId: Int, val isLiked: Boolean) : BaseUseCase.RequestValue

    override suspend fun executeUseCase(requestValues: RequestValue) {
        requestValues.apply {
            postRepository.updateIsLiked(postId, isLiked)
        }
    }
}