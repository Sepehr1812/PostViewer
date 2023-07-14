package com.pooyan.test.usecases

import com.pooyan.test.repos.PostRepository
import javax.inject.Inject

class UpdateLikes @Inject constructor(private val postRepository: PostRepository) :
    BaseUseCase<UpdateLikes.RequestValue, Unit>() {

    data class RequestValue(val postId: Int, val likes: Int) : BaseUseCase.RequestValue

    override suspend fun executeUseCase(requestValues: RequestValue) {
        requestValues.apply {
            postRepository.updateLikes(postId, likes)
        }
    }
}