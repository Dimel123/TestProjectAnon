package com.dmitry.grishin.testprojectanon.common.di

import android.content.Context
import com.dmitry.grishin.testprojectanon.App
import com.squareup.moshi.Moshi
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import toothpick.config.Module

class AppModule(app: App) : Module() {

    companion object {
        const val MAIN_SCHEDULER: String = "MAIN_SCHEDULER"
        const val IO_SCHEDULER: String = "IO_SCHEDULER"
    }

    init {
        bind(Context::class.java)
                .toInstance(app)

        bind(Moshi::class.java)
                .toInstance(Moshi.Builder()
                        .build())

        bind(Scheduler::class.java)
                .withName(MAIN_SCHEDULER)
                .toInstance(AndroidSchedulers.mainThread())

        bind(Scheduler::class.java)
                .withName(IO_SCHEDULER)
                .toInstance(Schedulers.io())
    }

}