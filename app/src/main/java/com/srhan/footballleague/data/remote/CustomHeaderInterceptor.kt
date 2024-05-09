package com.srhan.footballleague.data.remote

import com.srhan.footballleague.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class CustomHeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newBuilder = originalRequest.newBuilder()
            .header("X-Auth-Token", BuildConfig.TOKEN)
            .build()
        return chain.proceed(newBuilder)
    }
}