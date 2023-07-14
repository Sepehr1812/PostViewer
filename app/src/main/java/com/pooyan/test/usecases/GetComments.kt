package com.pooyan.test.usecases

import com.pooyan.test.data.models.Comment
import com.pooyan.test.repos.CommentRepository
import javax.inject.Inject

class GetComments @Inject constructor(private val commentRepository: CommentRepository) :
    BaseUseCase<GetComments.RequestValue, List<Comment>>() {

    data class RequestValue(val postId: Int, val count: Int) : BaseUseCase.RequestValue

    override suspend fun executeUseCase(requestValues: RequestValue) =
        requestValues.run {
            commentRepository.getComments(postId, count)
        }
}