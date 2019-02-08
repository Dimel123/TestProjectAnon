package com.dmitry.grishin.testprojectanon.data.network.repositories

import com.dmitry.grishin.testprojectanon.common.di.AppModule
import com.dmitry.grishin.testprojectanon.data.database.dao.IPostDao
import com.dmitry.grishin.testprojectanon.data.database.entities.PostEntity
import com.dmitry.grishin.testprojectanon.data.network.requests.GetPostsRequest
import com.dmitry.grishin.testprojectanon.data.network.services.PostService
import com.dmitry.grishin.testprojectanon.utils.mappers.mapPostResponseToPostEntity
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.functions.Function
import javax.inject.Inject
import javax.inject.Named

class PostRepository @Inject constructor(private val postService: PostService,
                                         private val postDao: IPostDao,
                                         @Named(AppModule.IO_SCHEDULER) private val ioScheduler: Scheduler) :
        IPostRepository {

    override fun getPosts(type: Int, limit: Int, offset: Int): Observable<List<PostEntity>> =
            Observable.concat(getNetworkData(type, limit, offset), getStorageData(limit, offset))
                    .first(arrayListOf())
                    .toObservable()
                    .subscribeOn(ioScheduler)

    private fun getNetworkData(type: Int, limit: Int, offset: Int) =
            postService.getPosts(GetPostsRequest(type, limit, offset))
                    .map { response -> mapPostResponseToPostEntity(response) }
                    .doOnNext { postDao.insert(it) }
                    .onErrorResumeNext(Function {
                        Observable.empty()
                    })

    private fun getStorageData(limit: Int, offset: Int) =
            Observable.fromCallable { postDao.getListEntities(limit, offset) }

}