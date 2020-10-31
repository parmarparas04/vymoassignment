package com.assignment.base.data.global

import okhttp3.Interceptor
import okhttp3.Response
import java.util.*

/**
 * Application Global Class to Set Global Allowed Params
 */
class AppGlobals : Globals {
    override var okHttpIntercepter: ((Interceptor.Chain) -> Response)? = null
    private var headersMap: WeakHashMap<String, String>? = null
    override fun getInterceptorHeaders(): MutableMap<String, String>? {
        if (headersMap == null)
            headersMap = WeakHashMap()
        return headersMap
    }
}