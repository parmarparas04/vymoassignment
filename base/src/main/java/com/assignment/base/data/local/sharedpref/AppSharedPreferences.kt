package com.assignment.base.data.local.sharedpref

/**
 * Abstract SharedPreferences Wrapper to save Key pair Values
 */
interface AppSharedPreferences {
    fun saveToken(token: String)
    fun getToken(): String?
    fun <T> setValue(key: String, value: T)
    fun saveJson(json:String?,key:String)
    fun getJson(key:String):String?
    fun getStringPre(key:String):String?
    fun getBooleanPre(key:String):Boolean

}