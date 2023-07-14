package com.pooyan.test.viewmodels

import androidx.lifecycle.ViewModel
import com.pooyan.test.usecases.GetComments
import com.pooyan.test.usecases.InsertComment
import com.pooyan.test.usecases.UpdateComments
import com.pooyan.test.usecases.UpdateLikes
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class PostViewModel @Inject constructor(
    private val insertComment: InsertComment,
    private val getComments: GetComments,
    private val updateLikes: UpdateLikes,
    private val updateComments: UpdateComments
) : ViewModel()