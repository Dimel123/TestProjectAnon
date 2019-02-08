package com.dmitry.grishin.testprojectanon.common.di

import com.dmitry.grishin.testprojectanon.data.network.di.MoshiConverterProvider
import com.dmitry.grishin.testprojectanon.data.network.di.OkHttpClientProvider
import com.dmitry.grishin.testprojectanon.data.network.di.PostServiceProvider
import com.dmitry.grishin.testprojectanon.data.network.di.RetrofitProvider
import com.dmitry.grishin.testprojectanon.data.network.repositories.IPostRepository
import com.dmitry.grishin.testprojectanon.data.network.repositories.PostRepository
import com.dmitry.grishin.testprojectanon.data.network.services.PostService
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import toothpick.config.Module


object NetworkModule : Module() {

    init {

        bind(OkHttpClient::class.java)
                .toProvider(OkHttpClientProvider::class.java)
                .singletonInScope()
        bind(Retrofit::class.java)
                .toProvider(RetrofitProvider::class.java)
                .singletonInScope()
        bind(Converter.Factory::class.java)
                .toProvider(MoshiConverterProvider::class.java)
        bind(PostService::class.java)
                .toProvider(PostServiceProvider::class.java)
                .singletonInScope()
        bind(IPostRepository::class.java)
                .to(PostRepository::class.java)
                .singletonInScope()
    }

}