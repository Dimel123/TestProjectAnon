package com.dmitry.grishin.testprojectanon.presentation.main

import com.dmitry.grishin.testprojectanon.presentation.models.Post

interface IMainView {

    fun showData(list: List<Post>)

    fun showError()

    fun showProgress()

}