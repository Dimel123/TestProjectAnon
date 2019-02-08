package com.dmitry.grishin.testprojectanon.data.network.di

import com.dmitry.grishin.testprojectanon.data.network.services.PostService
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Provider

class PostServiceProvider @Inject constructor(private val retrofit: Retrofit):
        Provider<PostService> {

    override fun get(): PostService = retrofit.create(PostService::class.java)

}