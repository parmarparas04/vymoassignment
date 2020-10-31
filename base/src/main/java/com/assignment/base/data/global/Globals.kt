package com.assignment.base.data.global


import okhttp3.Interceptor
import okhttp3.Response


interface Globals {

    var okHttpIntercepter: ((Interceptor.Chain) -> Response)?
    /**
     * RetroFit Intercept Headers to be Use While Invoking any Api
     * @return MutableMap of Headers
     */
    fun getInterceptorHeaders(): MutableMap<String, String>?
}