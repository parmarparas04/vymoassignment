package com.assignment.base.data.utils
import com.assignment.base.data.global.Globals
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response


internal class RestRequestInterceptor(val globals: Globals) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val interceptedResponse = globals.okHttpIntercepter?.invoke(chain)
        return if(interceptedResponse != null){
            interceptedResponse
        }else
        {
            val newBuilder: Request.Builder? = chain.request().newBuilder()

            globals.getInterceptorHeaders()?.forEach {
                newBuilder?.removeHeader(it.key)
                newBuilder?.addHeader(it.key, it.value)
            }

            chain.proceed(
                newBuilder!!.build()
            )
        }
    }
}