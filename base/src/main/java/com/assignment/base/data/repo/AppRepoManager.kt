package com.assignment.base.data.repo

import com.assignment.base.data.api.ApiPool
import com.assignment.base.data.repo.RepoManager
import com.assignment.base.data.global.Globals
import com.assignment.base.data.local.sharedpref.AppSharedPreferences
import retrofit2.Retrofit

/**
 * The Main Implementation of all kind of data management class which provide all supported type of data provider.
 */
internal class AppRepoManager(
    private val retrofit: Retrofit.Builder,
    private val sharedPreferences: AppSharedPreferences,
    private val apiPool: ApiPool,
    private val globals: Globals
) : RepoManager {
    override fun <T> getApi(baseUrl: String, apiClass: Class<T>): T {
        return if(apiPool.contains(baseUrl)
            && apiPool[baseUrl] != null
            && apiPool[baseUrl]!!::class.java.isAssignableFrom(apiClass))
             apiPool[baseUrl] as T
        else{
            val api = retrofit.baseUrl(baseUrl).build().create(apiClass)
            apiPool[baseUrl] = api
            api
        }
    }


    override fun getGlobals(): Globals {
        return globals
    }
    override fun getSharedPreferences(): AppSharedPreferences {
        return sharedPreferences
    }


}