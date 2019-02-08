package com.dmitry.grishin.testprojectanon.data.network.services

import com.dmitry.grishin.testprojectanon.data.network.requests.GetPostsRequest
import com.dmitry.grishin.testprojectanon.data.network.response.PostResponse
import com.dmitry.grishin.testprojectanon.data.network.response.Response
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface PostService {

    @POST("posts/get")
    fun getPosts(@Body getPostsRequest: GetPostsRequest): Observable<Response<List<PostResponse>>>

}