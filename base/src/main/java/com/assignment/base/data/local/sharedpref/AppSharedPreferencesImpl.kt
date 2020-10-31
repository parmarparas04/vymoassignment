package com.assignment.base.data.local.sharedpref

import android.content.SharedPreferences

/**
 * SharedPreferences Wrapper to save Key pair Values
 */
class AppSharedPreferencesImpl(private val sharedPreferences: SharedPreferences) :
    AppSharedPreferences {
    override fun saveToken(token: String)  {
        sharedPreferences.edit().putString("USER_TOKEN", token).apply()
    }

    override fun getToken(): String? = sharedPreferences.getString("USER_TOKEN", "")

    override fun <T> setValue(key: String, value: T) {
        val editor = sharedPreferences.edit()
        when (value) {
            is Boolean -> {
                editor.putBoolean(key, value as Boolean)
            }
            is String -> {
                editor.putString(key, value as String)
            }
            is Long -> {
                editor.putLong(key, value as Long)
            }
        }

        editor.apply()
    }

    override fun getJson(key: String):String? {
        return sharedPreferences.getString(key, "")
    }

    override fun saveJson(json: String?, key: String) {
        sharedPreferences.edit().putString(key, json).apply()
    }

    override fun getStringPre(key: String): String? {
    return sharedPreferences.getString(key, "")
    }

    override fun getBooleanPre(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }
}