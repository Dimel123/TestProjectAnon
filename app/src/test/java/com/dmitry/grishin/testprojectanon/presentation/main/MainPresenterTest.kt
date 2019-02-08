package com.dmitry.grishin.testprojectanon.presentation.main

import com.dmitry.grishin.testprojectanon.domain.IPostInteractor
import com.nhaarman.mockitokotlin2.any
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class MainPresenterTest {

    companion object {
        const val TYPE_DATA = 2
        const val LIMIT = 10
        const val OFFSET = 0
    }

    private lateinit var presenter: MainPresenter
    private lateinit var view: IMainView
    private lateinit var postInteractor: IPostInteractor

    @Before
    fun setUp() {
        view = Mockito.mock(IMainView::class.java)
        postInteractor = Mockito.mock(IPostInteractor::class.java)
        presenter = MainPresenter(
                view,
                postInteractor,
                Schedulers.trampoline())
    }

    @Test
    fun onAttachView_whenGetPostsFail_showError() {
        val error = RuntimeException("EXCEPTION")
        Mockito.`when`(postInteractor.getPosts(TYPE_DATA, LIMIT, OFFSET)).thenReturn(Observable.error(error))

        presenter.onAttachView(TYPE_DATA, LIMIT, OFFSET)

        Mockito.verify(view).showProgress()
        Mockito.verify(view).showError()
        Mockito.verifyNoMoreInteractions(view)
    }

    @Test
    fun onAttachView_whenGetPostsSuccess_showData() {
        Mockito.`when`(postInteractor.getPosts(TYPE_DATA, LIMIT, OFFSET)).thenReturn(Observable.just(arrayListOf()))

        presenter.onAttachView(TYPE_DATA, LIMIT, OFFSET)

        Mockito.verify(view).showProgress()
        Mockito.verify(view).showData(any())
        Mockito.verifyNoMoreInteractions(view)
    }


}