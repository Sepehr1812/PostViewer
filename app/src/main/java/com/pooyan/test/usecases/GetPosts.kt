package com.pooyan.test.usecases

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.pooyan.test.repos.PostPagingSource
import com.pooyan.test.repos.PostRepository
import javax.inject.Inject

class GetPosts @Inject constructor(private val postRepository: PostRepository) {

    fun executeUseCase() = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = {
            PostPagingSource(postRepository)
        }
    ).flow
}