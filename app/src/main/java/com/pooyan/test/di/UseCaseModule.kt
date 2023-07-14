package com.pooyan.test.di

import com.pooyan.test.repos.CommentRepository
import com.pooyan.test.repos.PostRepository
import com.pooyan.test.usecases.GetComments
import com.pooyan.test.usecases.GetPosts
import com.pooyan.test.usecases.InsertComment
import com.pooyan.test.usecases.InsertCommentList
import com.pooyan.test.usecases.InsertPosts
import com.pooyan.test.usecases.UpdateComments
import com.pooyan.test.usecases.UpdateLikes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideInsertPosts(postRepository: PostRepository) = InsertPosts(postRepository)

    @Provides
    @ViewModelScoped
    fun provideGetPosts(postRepository: PostRepository) = GetPosts(postRepository)

    @Provides
    @ViewModelScoped
    fun provideUpdateLikes(postRepository: PostRepository) = UpdateLikes(postRepository)

    @Provides
    @ViewModelScoped
    fun provideUpdateComments(postRepository: PostRepository) = UpdateComments(postRepository)

    @Provides
    @ViewModelScoped
    fun provideInsertCommentList(commentRepository: CommentRepository) =
        InsertCommentList(commentRepository)

    @Provides
    @ViewModelScoped
    fun provideInsertComment(commentRepository: CommentRepository) =
        InsertComment(commentRepository)

    @Provides
    @ViewModelScoped
    fun provideGetComments(commentRepository: CommentRepository) = GetComments(commentRepository)
}