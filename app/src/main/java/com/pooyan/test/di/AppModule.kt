package com.pooyan.test.di

import android.content.Context
import androidx.room.Room
import com.pooyan.test.data.db.ApplicationDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) =
        synchronized(ApplicationDatabase::class) {
            Room.databaseBuilder(
                context,
                ApplicationDatabase::class.java,
                "PooyanDatabase"
            ).build()
        }

    @Singleton
    @Provides
    fun providePostDao(applicationDatabase: ApplicationDatabase) = applicationDatabase.postDao()

    @Singleton
    @Provides
    fun provideCommentDao(applicationDatabase: ApplicationDatabase) =
        applicationDatabase.commentDao()
}