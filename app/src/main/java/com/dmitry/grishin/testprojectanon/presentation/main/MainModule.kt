package com.dmitry.grishin.testprojectanon.presentation.main

import toothpick.config.Module

class MainModule(val view: IMainView) : Module() {
    init {
        bind(IMainView::class.java).toInstance(view)
    }
}