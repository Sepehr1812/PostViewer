package com.pooyan.test.data.mappers

import com.pooyan.test.data.db.entities.PostEntity
import com.pooyan.test.data.models.Post

object PostMapper {

    fun toDomain(postEntity: PostEntity) = postEntity.run {
        Post(id, user, caption, likes, comments, image, isLiked)
    }

    fun fromDomain(post: Post) = post.run {
        PostEntity(user, caption, likes, comments, image, isLiked, id)
    }
}