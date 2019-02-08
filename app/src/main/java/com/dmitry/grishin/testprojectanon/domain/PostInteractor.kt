package com.dmitry.grishin.testprojectanon.domain

import com.dmitry.grishin.testprojectanon.data.database.entities.PostEntity
import com.dmitry.grishin.testprojectanon.data.network.repositories.IPostRepository
import io.reactivex.Observable
import javax.inject.Inject

class PostInteractor @Inject constructor(
        private val postRepository: IPostRepository) : IPostInteractor {

    override fun getPosts(typeOfData: Int, limit: Int, offset: Int): Observable<List<PostEntity>> =
            postRepository.getPosts(typeOfData, limit, offset)

}