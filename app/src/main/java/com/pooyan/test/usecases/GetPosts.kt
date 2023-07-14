package com.pooyan.test.usecases

import com.pooyan.test.data.models.Post
import com.pooyan.test.repos.PostRepository
import javax.inject.Inject

class GetPosts @Inject constructor(private val postRepository: PostRepository) :
    BaseUseCase<GetPosts.RequestValue, List<Post>>() {

    data class RequestValue(val count: Int) : BaseUseCase.RequestValue

    override suspend fun executeUseCase(requestValues: RequestValue) =
        postRepository.getPosts(requestValues.count)
}