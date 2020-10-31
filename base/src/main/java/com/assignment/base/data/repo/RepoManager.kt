package com.assignment.base.data.repo

import com.assignment.base.data.global.Globals
import com.assignment.base.data.local.sharedpref.AppSharedPreferences


/**
 * The abstract Implementation of all kind of data management class which provide all supported type of data provider.
 */
interface RepoManager {
    fun <T> getApi(baseUrl:String,apiClass: Class<T>): T

    fun getSharedPreferences() : AppSharedPreferences

    fun getGlobals() : Globals
}