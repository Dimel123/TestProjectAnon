package com.dmitry.grishin.testprojectanon.data.network.response

data class Response<out T>(val error: Boolean, val code: Int, val data: T)