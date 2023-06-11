package com.example.cheat.pref

import android.content.Context

class UserPreference(context: Context) {

    private val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun saveCookie(cookie: String) {
        val editor = preferences.edit()
        editor.putString(USER_COOKIE, cookie)
        editor.apply()
    }

    fun getCookie() : String? {
        return preferences.getString(USER_COOKIE, null)
    }

    fun deleteCookie() {
        preferences.edit().clear().apply()
    }

    companion object {
        private const val PREFS_NAME = "PREFS_NAME"
        private const val USER_COOKIE = "USER_COOKIE"
    }
}