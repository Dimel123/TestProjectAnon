package com.dmitry.grishin.testprojectanon.presentation.main

import android.util.Log
import com.dmitry.grishin.testprojectanon.common.di.AppModule
import com.dmitry.grishin.testprojectanon.domain.IPostInteractor
import com.dmitry.grishin.testprojectanon.utils.mappers.mapPostEntityToPost
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposables
import javax.inject.Inject
import javax.inject.Named

class MainPresenter @Inject constructor(private val view: IMainView,
                                        private val postInteractor: IPostInteractor,
                                        @Named(AppModule.MAIN_SCHEDULER) private val mainScheduler: Scheduler) {

    private var postDisposable = Disposables.disposed()

    fun onAttachView(typeOfData: Int, limit: Int, offset: Int) {
        view.showProgress()
        postDisposable = postInteractor.getPosts(typeOfData, limit, offset)
                .observeOn(mainScheduler)
                .map { mapPostEntityToPost(it) }
                .subscribe({
                    view.showData(it)
                }, {
                    view.showError()
                })
    }

    fun onDetachView() {
        postDisposable.dispose()
    }
}