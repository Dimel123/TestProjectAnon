package com.dmitry.grishin.testprojectanon.domain

import com.dmitry.grishin.testprojectanon.data.database.entities.PostEntity
import io.reactivex.Observable

interface IPostInteractor {

    fun getPosts(typeOfData: Int, limit: Int, offset: Int): Observable<List<PostEntity>>

}