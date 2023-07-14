package com.pooyan.test.viewmodels

import androidx.lifecycle.ViewModel
import com.pooyan.test.usecases.GetPosts
import com.pooyan.test.usecases.InsertCommentList
import com.pooyan.test.usecases.InsertPosts
import com.pooyan.test.usecases.UpdateLikes
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostListViewModel @Inject constructor(
    private val insertPosts: InsertPosts,
    private val insertCommentList: InsertCommentList,
    private val getPosts: GetPosts,
    private val updateLikes: UpdateLikes
) : ViewModel()