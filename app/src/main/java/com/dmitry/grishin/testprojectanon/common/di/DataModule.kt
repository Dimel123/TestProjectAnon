package com.dmitry.grishin.testprojectanon.common.di

import com.dmitry.grishin.testprojectanon.data.database.AppDatabase
import com.dmitry.grishin.testprojectanon.data.database.dao.IPostDao
import com.dmitry.grishin.testprojectanon.data.database.di.AppDatabaseProvider
import com.dmitry.grishin.testprojectanon.data.database.di.PostDaoProvider
import toothpick.config.Module

object DataModule: Module() {

    init {
        bind(AppDatabase::class.java)
                .toProvider(AppDatabaseProvider::class.java)
                .singletonInScope()

        bind(IPostDao::class.java)
                .toProvider(PostDaoProvider::class.java)
                .singletonInScope()
    }

}