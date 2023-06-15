package com.example.cheat.pref

import android.content.Context

class UserPreference(context: Context) {

    private val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    private val usernamePreferences = context.getSharedPreferences(PREFS_USER_NAME, Context.MODE_PRIVATE)
    private val onBoardingStatus = context.getSharedPreferences(PREFS_ON_BOARDING, Context.MODE_PRIVATE)

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

    fun saveUsername(username: String) {
        val editor = usernamePreferences.edit()
        editor.putString(USER_NAME, username)
        editor.apply()
    }

    fun getUsername(): String? {
        return usernamePreferences.getString(USER_NAME, null)
    }

    fun deleteUsername() {
        preferences.edit().clear().apply()
    }

    fun saveOnBoardingStatus(id: String) {
        val editor = onBoardingStatus.edit()
        editor.putString(USER_ON_BOARDING, id)
        editor.apply()
    }

    fun getOnBoardingStatus(): String? {
        return onBoardingStatus.getString(USER_ON_BOARDING, null)
    }

    companion object {
        private const val PREFS_NAME = "PREFS_NAME"
        private const val PREFS_USER_NAME = "PREFS_USER_NAME"
        private const val PREFS_ON_BOARDING = "PREFS_ON_BOARDING"
        private const val USER_COOKIE = "USER_COOKIE"
        private const val USER_NAME = "USER_NAME"
        private const val USER_ON_BOARDING = "USER_ON_BOARDING"
    }
}