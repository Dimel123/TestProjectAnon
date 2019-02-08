package com.dmitry.grishin.testprojectanon.presentation.main

import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler
import com.dmitry.grishin.testprojectanon.R
import com.dmitry.grishin.testprojectanon.common.di.Scopes.APP_SCOPE
import com.dmitry.grishin.testprojectanon.common.di.Scopes.MAIN_SCOPE
import com.dmitry.grishin.testprojectanon.presentation.detail.DetailController
import com.dmitry.grishin.testprojectanon.presentation.main.adapter.PostsAdapter
import com.dmitry.grishin.testprojectanon.presentation.models.Post
import com.dmitry.grishin.testprojectanon.widgets.PagingLayoutManager
import com.dmitry.grishin.testprojectanon.widgets.ViewStateHelper
import kotlinx.android.synthetic.main.controller_main.view.*
import toothpick.Lazy
import toothpick.Toothpick
import javax.inject.Inject


class MainController : Controller(), IMainView {

    companion object {
        const val TYPE_OF_DATA = 2
        const val LIMIT = 30
    }

    @Inject
    lateinit var presenterProvider: Lazy<MainPresenter>
    val presenter: MainPresenter by lazy {
        presenterProvider.get()
    }

    private lateinit var postAdapter: PostsAdapter
    private lateinit var viewStateHelper: ViewStateHelper
    private var isLoad = false
    private var postsList = arrayListOf<Post>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.controller_main, container, false)

        Toothpick.openScopes(APP_SCOPE, MAIN_SCOPE).apply {
            installModules(MainModule(this@MainController))
            Toothpick.inject(this@MainController, this)
        }

        viewStateHelper = ViewStateHelper(view.list, view.progress, view.error)

        setupAdapter(view)
        setupListeners(view)

        return view
    }

    override fun onAttach(view: View) {
        if (postsList.size == 0) {
            presenter.onAttachView(TYPE_OF_DATA, LIMIT, 0)
        } else {
            viewStateHelper.showData()
            postAdapter.submitList(postsList)
        }
    }

    override fun onDetach(view: View) {
        presenter.onDetachView()
    }

    override fun onDestroy() {
        Toothpick.closeScope(MAIN_SCOPE)
    }

    override fun showData(list: List<Post>) {
        if(postsList.isEmpty() && list.isEmpty()){
            showError()
            return
        }

        viewStateHelper.showData()

        isLoad = false
        postsList.addAll(list)
        postAdapter.submitList(postsList)
    }

    override fun showError() {
        viewStateHelper.showError()
    }

    override fun showProgress() {
        if (postAdapter.itemCount == 0) {
            viewStateHelper.showProgress()
        }
    }

    private fun setupAdapter(view: View) {
        view.apply {
            val layoutManager = PagingLayoutManager(context)
            list.layoutManager = layoutManager
            postAdapter = PostsAdapter(object : PostsAdapter.IPostClickListener {
                override fun onItemClick(post: Post) {
                    startDetailController(post)
                }
            })

            list.addItemDecoration(DividerItemDecoration(context, layoutManager.orientation))

            list.adapter = postAdapter
            list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (layoutManager.isOnNextPosition() && !isLoad) {
                        isLoad = true
                        presenter.onAttachView(TYPE_OF_DATA, LIMIT, postAdapter.itemCount)
                    }
                }
            })
        }
    }

    private fun setupListeners(view: View) {
        view.error.setOnClickListener { presenter.onAttachView(TYPE_OF_DATA, LIMIT, 0) }
    }

    private fun startDetailController(post: Post) {
        router.pushController(RouterTransaction.with(DetailController.newInstance(post))
                .popChangeHandler(FadeChangeHandler())
                .pushChangeHandler(FadeChangeHandler()))
    }

}