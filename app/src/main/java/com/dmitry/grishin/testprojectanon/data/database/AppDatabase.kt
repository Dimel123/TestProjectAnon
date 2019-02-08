package com.dmitry.grishin.testprojectanon.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.dmitry.grishin.testprojectanon.data.database.dao.IPostDao
import com.dmitry.grishin.testprojectanon.data.database.entities.PostEntity

const val DATABASE_VERSION = 11

@Database(entities = [(PostEntity::class)], version = DATABASE_VERSION, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): IPostDao
}