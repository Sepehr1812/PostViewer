package com.pooyan.test.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pooyan.test.data.db.dao.CommentDao
import com.pooyan.test.data.db.dao.PostDao
import com.pooyan.test.data.db.entities.CommentEntity
import com.pooyan.test.data.db.entities.PostEntity

@Database(entities = [PostEntity::class, CommentEntity::class], version = 1, exportSchema = false)
abstract class ApplicationDatabase : RoomDatabase() {

    abstract fun postDao(): PostDao

    abstract fun commentDao(): CommentDao
}