package com.pooyan.test.repos

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pooyan.test.data.models.Post
import kotlin.math.min

class PostPagingSource(private val postRepository: PostRepository) : PagingSource<Int, Post>() {

    override fun getRefreshKey(state: PagingState<Int, Post>) = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>) = try {
        val page = params.key ?: 0
        val size = min(params.loadSize, 10)
        val offset = page * size
        val data = postRepository.getPosts(count = size, offset = offset)

        LoadResult.Page(
            data = data,
            prevKey = if (page == 0) null else page.minus(1),
            nextKey = if (data.isEmpty()) null else page.plus(1)
        )
    } catch (e: Exception) {
        LoadResult.Error(e)
    }
}