package com.dmitry.grishin.testprojectanon.data.network.repositories

import com.dmitry.grishin.testprojectanon.data.database.entities.PostEntity
import io.reactivex.Observable
import io.reactivex.Single

interface IPostRepository {

    fun getPosts(type: Int, limit: Int, offset: Int): Observable<List<PostEntity>>

}