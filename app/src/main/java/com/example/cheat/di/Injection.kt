package com.example.cheat.di

import android.content.Context
import com.example.cheat.data.remote.retrofit.ApiConfig
import com.example.cheat.pref.UserPreference
import com.example.cheat.repository.CheatRepository

object Injection {
    fun provideRepository(context: Context) : CheatRepository {
        val apiService = ApiConfig.getApiService()
        val userPreference = UserPreference(context)
        return CheatRepository(userPreference, apiService)
    }
}