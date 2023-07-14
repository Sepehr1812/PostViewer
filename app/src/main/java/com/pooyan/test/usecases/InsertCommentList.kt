package com.pooyan.test.usecases

import com.pooyan.test.data.models.Comment
import com.pooyan.test.repos.CommentRepository
import javax.inject.Inject

class InsertCommentList @Inject constructor(private val commentRepository: CommentRepository) :
    BaseUseCase<InsertCommentList.RequestValue, Unit>() {

    data class RequestValue(val commentList: List<Comment>) : BaseUseCase.RequestValue

    override suspend fun executeUseCase(requestValues: RequestValue) {
        commentRepository.insertCommentList(requestValues.commentList)
    }
}