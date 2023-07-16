package com.pooyan.test.utils

import com.pooyan.test.R
import com.pooyan.test.data.models.Comment
import com.pooyan.test.data.models.Post
import kotlin.random.Random

object DataUtil {

    fun generateUsernames(): List<String> {
        val alphanumeric = ('a'..'z') + ('0'..'9')

        return mutableListOf<String>().apply {
            repeat(20) {
                add(List(5) { alphanumeric.random() }.joinToString(""))
            }
        }
    }

    fun generateSamplePostData(usernameList: List<String>, commentList: List<Comment>) =
        mutableListOf<Post>().apply {
            repeat(30) {
                add(
                    Post(
                        it,
                        usernameList.random(),
                        """Onoda was born on 19 March 1922,
                        | in Kamekawa Village, Kaisō District, Wakayama Prefecture, Japan.
                        |  When he was 17 years old, he went to work for the Tajima Yoko trading company in Wuhan, China.
                        |  When he was 18, he enlisted in the Imperial Japanese Army Infantry.""".trimMargin(),
                        Random.nextInt(10, 1000),
                        // detecting comments count
                        commentList.groupBy { comment -> comment.postId }[it]?.size ?: 0,
                        listOf(R.drawable.profile_bg_men, R.drawable.profile_bg_women).random()
                    )
                )
            }
        }

    fun generateSampleCommentData(usernameList: List<String>) = mutableListOf<Comment>().apply {
        repeat(300) {
            add(
                Comment(
                    it,
                    usernameList.random(),
                    "Onoda was born on 19 March 2022",
                    Random.nextInt(30)
                )
            )
        }
    }
}