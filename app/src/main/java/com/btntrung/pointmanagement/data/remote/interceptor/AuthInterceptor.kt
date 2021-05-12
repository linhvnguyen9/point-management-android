package com.btntrung.pointmanagement.data.remote.interceptor

import com.orhanobut.hawk.Hawk
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = Hawk.get("FIREBASE_TOKEN", "")
        val request = chain.request()
        val authenticateRequest = request.newBuilder()
            .addHeader("Authorization", "Bearer $token").build()
        return chain.proceed(authenticateRequest)
    }
}
