package com.pooyan.test.utils

object Routes {
    const val POST_LIST_SCREEN = "PostListScreen"
    const val POST_SCREEN = "PostScreen/{${Values.POST_SCREEN_VALUE}}"

    fun getPostScreenPath(postId: Int) = "PostScreen/$postId"

    object Values {
        const val POST_SCREEN_VALUE = "postId"
    }
}