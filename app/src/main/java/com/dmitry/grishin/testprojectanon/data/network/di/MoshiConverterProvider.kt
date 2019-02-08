package com.dmitry.grishin.testprojectanon.data.network.di

import com.squareup.moshi.Moshi
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject
import javax.inject.Provider

class MoshiConverterProvider @Inject constructor(val moshi: Moshi) : Provider<MoshiConverterFactory> {

    override fun get(): MoshiConverterFactory = MoshiConverterFactory.create(moshi)

}