package com.dmitry.grishin.testprojectanon.common.di

import com.dmitry.grishin.testprojectanon.domain.IPostInteractor
import com.dmitry.grishin.testprojectanon.domain.PostInteractor
import toothpick.config.Module

object DomainModule: Module() {

    init {
        bind(IPostInteractor::class.java)
                .to(PostInteractor::class.java)
                .singletonInScope()
    }

}