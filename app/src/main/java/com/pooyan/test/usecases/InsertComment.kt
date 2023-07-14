package com.pooyan.test.usecases

import com.pooyan.test.data.models.Comment
import com.pooyan.test.repos.CommentRepository
import javax.inject.Inject

class InsertComment @Inject constructor(private val commentRepository: CommentRepository) :
    BaseUseCase<InsertComment.RequestValue, Unit>() {

    data class RequestValue(val comment: Comment) : BaseUseCase.RequestValue

    override suspend fun executeUseCase(requestValues: RequestValue) {
        commentRepository.insertComment(requestValues.comment)
    }
}