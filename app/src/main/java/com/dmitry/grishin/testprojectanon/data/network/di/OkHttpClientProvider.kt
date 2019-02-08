package com.dmitry.grishin.testprojectanon.data.network.di

import com.dmitry.grishin.testprojectanon.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Provider

class OkHttpClientProvider @Inject constructor(): Provider<OkHttpClient> {

    companion object {
        const val CONNECT_TIMEOUT = 40L
        const val READ_TIMEOUT = 40L
        const val WRITE_TIMEOUT = 40L

        const val CONTENT_TYPE = "content-type"
        const val CONTENT_TYPE_VALUE = "application/json; charset=UTF-8"
    }

    override fun get(): OkHttpClient {
        val builder = OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
        addHeaders(builder)
        addLogger(builder)

        return builder.build()
    }
    private fun addHeaders(builder: OkHttpClient.Builder) {
        builder.addInterceptor { chain ->
            val request = chain.request().newBuilder()
                    .addHeader(CONTENT_TYPE, CONTENT_TYPE_VALUE)
                    .build()
            chain.proceed(request)
        }
    }

    private fun addLogger(builder: OkHttpClient.Builder) {
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }
    }

}