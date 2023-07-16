package com.pooyan.test.repos

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pooyan.test.data.models.Comment
import kotlin.math.min

class CommentPagingSource(
    private val commentRepository: CommentRepository,
    private val postId: Int
) : PagingSource<Int, Comment>() {

    override fun getRefreshKey(state: PagingState<Int, Comment>) = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>) = try {
        val page = params.key ?: 0
        val size = min(params.loadSize, 10)
        val offset = page * size
        val data = commentRepository.getComments(postId = postId, count = size, offset = offset)

        LoadResult.Page(
            data = data,
            prevKey = if (page == 0) null else page.minus(1),
            nextKey = if (data.isEmpty()) null else page.plus(1)
        )
    } catch (e: Exception) {
        LoadResult.Error(e)
    }
}