package com.pooyan.test.data.mappers

import com.pooyan.test.data.db.entities.CommentEntity
import com.pooyan.test.data.models.Comment

object CommentMapper {

    fun toDomain(commentEntity: CommentEntity) = commentEntity.run {
        Comment(id, user, content, postId)
    }

    fun fromDomain(comment: Comment) = comment.run {
        CommentEntity(user, content, postId, id)
    }
}