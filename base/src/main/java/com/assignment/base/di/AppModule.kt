package com.assignment.base.di

import android.content.Context
import com.assignment.base.components.BaseViewModelFactory
import com.assignment.base.data.api.ApiPool
import com.assignment.base.data.global.AppGlobals
import com.assignment.base.data.global.Globals
import com.assignment.base.data.local.sharedpref.AppSharedPreferences
import com.assignment.base.data.local.sharedpref.AppSharedPreferencesImpl
import com.assignment.base.data.repo.AppRepoManager
import com.assignment.base.data.repo.RepoManager
import com.google.gson.GsonBuilder
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit

/**
 * Koin App Modules ready to do some magic
 */
val appModule = module {
    single {
        //GSON
        GsonBuilder().setLenient().create()
    }
    single<Retrofit.Builder> {
        NetworkService().getRetrofitBuilder(get(), true,get(),androidContext())
    }

    single<Globals> {
        AppGlobals()
    }

    single<RepoManager> {
        // AppRepoManager
        AppRepoManager(get(), get(), get(),get())
    }


    single<AppSharedPreferences> {
        AppSharedPreferencesImpl(
            androidApplication().getSharedPreferences(
                "app",
                Context.MODE_PRIVATE
            )
        )
    }
    single<ApiPool>{
        ApiPool()
    }

    single {
        BaseViewModelFactory(get())
    }


}