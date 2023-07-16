package com.pooyan.test.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.pooyan.test.data.models.Comment
import com.pooyan.test.data.models.Post
import com.pooyan.test.usecases.GetComments
import com.pooyan.test.usecases.GetPost
import com.pooyan.test.usecases.InsertComment
import com.pooyan.test.usecases.UpdateComments
import com.pooyan.test.usecases.UpdateIsLiked
import com.pooyan.test.usecases.UpdateLikes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PostViewModel @Inject constructor(
    private val insertComment: InsertComment,
    private val getPost: GetPost,
    private val getComments: GetComments,
    private val updateLikes: UpdateLikes,
    private val updateIsLiked: UpdateIsLiked,
    private val updateComments: UpdateComments
) : ViewModel() {

    var postResponse by mutableStateOf<Post?>(null)
        private set

    fun insertComment(comment: Comment) {
        viewModelScope.launch {
            insertComment.executeUseCase(InsertComment.RequestValue(comment))
        }
    }

    fun getPost(postId: Int) {
        viewModelScope.launch {
            postResponse = getPost.executeUseCase(GetPost.RequestValue(postId))
        }
    }

    fun getComments(postId: Int) =
        getComments.executeUseCase(GetComments.RequestValue(postId)).cachedIn(viewModelScope)

    fun updateComments(postId: Int, comments: Int) {
        viewModelScope.launch {
            updateComments.executeUseCase(UpdateComments.RequestValue(postId, comments))
        }
    }

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