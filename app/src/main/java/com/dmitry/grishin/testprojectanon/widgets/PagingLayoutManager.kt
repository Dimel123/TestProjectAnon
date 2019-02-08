package com.dmitry.grishin.testprojectanon.widgets

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.util.AttributeSet


class PagingLayoutManager : LinearLayoutManager {

    constructor(context: Context) : super(context) {}

    constructor(context: Context, orientation: Int, reverseLayout: Boolean) : super(context, orientation, reverseLayout) {}

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {}

    fun isOnNextPosition(): Boolean {
        val visibleItemCount = childCount
        val totalItemCount = itemCount
        val pastVisibleItems = findFirstVisibleItemPosition()

        return visibleItemCount + pastVisibleItems >= totalItemCount / 2
    }

}