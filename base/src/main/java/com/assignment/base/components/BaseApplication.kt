package com.assignment.base.components

import androidx.multidex.MultiDexApplication
import com.assignment.base.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

abstract class BaseApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BaseApplication)
            modules(getKoinModules())
            if(isDebug())
                printLogger(Level.DEBUG)
        }

    }


    abstract fun isDebug() : Boolean

    open fun getKoinModules() = mutableListOf(appModule)

}