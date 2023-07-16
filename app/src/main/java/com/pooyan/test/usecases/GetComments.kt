package com.pooyan.test.usecases

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.pooyan.test.repos.CommentPagingSource
import com.pooyan.test.repos.CommentRepository
import javax.inject.Inject

class GetComments @Inject constructor(private val commentRepository: CommentRepository) {

    data class RequestValue(val postId: Int)

    fun executeUseCase(requestValues: RequestValue) = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = {
            CommentPagingSource(commentRepository, requestValues.postId)
        }
    ).flow
}