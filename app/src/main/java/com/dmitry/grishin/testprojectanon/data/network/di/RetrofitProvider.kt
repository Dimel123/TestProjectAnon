package com.dmitry.grishin.testprojectanon.data.network.di

import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import javax.inject.Inject
import javax.inject.Provider

class RetrofitProvider @Inject constructor(private var client: OkHttpClient,
        private var converterFactory: Converter.Factory) : Provider<Retrofit> {

    companion object {
        const val API_URL = "http://dev.apianon.ru:3000/"
    }

    override fun get(): Retrofit {
        return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .baseUrl(API_URL)
                .client(client)
                .addConverterFactory(converterFactory)
                .build()
    }

}