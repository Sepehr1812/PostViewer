package com.pooyan.test.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.pooyan.test.data.models.Comment
import com.pooyan.test.data.models.Post
import com.pooyan.test.usecases.GetPosts
import com.pooyan.test.usecases.InsertCommentList
import com.pooyan.test.usecases.InsertPosts
import com.pooyan.test.usecases.UpdateIsLiked
import com.pooyan.test.usecases.UpdateLikes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostListViewModel @Inject constructor(
    private val insertPosts: InsertPosts,
    private val insertCommentList: InsertCommentList,
    private val getPosts: GetPosts,
    private val updateLikes: UpdateLikes,
    private val updateIsLiked: UpdateIsLiked
) : ViewModel() {

    fun insertPosts(postList: List<Post>) {
        viewModelScope.launch {
            insertPosts.executeUseCase(InsertPosts.RequestValue(postList))
        }
    }

    fun insertCommentList(commentList: List<Comment>) {
        viewModelScope.launch {
            insertCommentList.executeUseCase(InsertCommentList.RequestValue(commentList))
        }
    }

    fun getPosts() = getPosts.executeUseCase().cachedIn(viewModelScope)

    fun updateLikes(postId: Int, likes: Int) {
        viewModelScope.launch {
            updateLikes.executeUseCase(UpdateLikes.RequestValue(postId, likes))
        }
    }

    fun updateIsLiked(postId: Int, isLiked: Boolean) {
        viewModelScope.launch {
            updateIsLiked.executeUseCase(UpdateIsLiked.RequestValue(postId, isLiked))
        }
    }
}