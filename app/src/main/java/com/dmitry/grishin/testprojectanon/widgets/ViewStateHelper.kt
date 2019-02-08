package com.dmitry.grishin.testprojectanon.widgets

import android.view.View
import android.widget.ProgressBar

class ViewStateHelper(val data: View, val progress: ProgressBar, val error: View) {

    fun showProgress() {
        progress.visibility = View.VISIBLE
        data.visibility = View.INVISIBLE
        error.visibility = View.INVISIBLE
    }

    fun showError() {
        progress.visibility = View.INVISIBLE
        data.visibility = View.INVISIBLE
        error.visibility = View.VISIBLE
    }

    fun showData() {
        progress.visibility = View.INVISIBLE
        data.visibility = View.VISIBLE
        error.visibility = View.INVISIBLE
    }

}