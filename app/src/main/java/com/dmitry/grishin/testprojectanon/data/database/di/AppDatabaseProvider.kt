package com.dmitry.grishin.testprojectanon.data.database.di

import android.arch.persistence.room.Room
import android.content.Context
import com.dmitry.grishin.testprojectanon.data.database.AppDatabase
import com.dmitry.grishin.testprojectanon.data.database.dao.IPostDao
import javax.inject.Inject
import javax.inject.Provider

const val DATABASE_NAME: String = "database"

class AppDatabaseProvider @Inject constructor(private val context: Context) : Provider<AppDatabase> {
    override fun get() =
            Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
}

class PostDaoProvider @Inject constructor(private val appDatabase: AppDatabase) : Provider<IPostDao> {
    override fun get() = appDatabase.postDao()
}