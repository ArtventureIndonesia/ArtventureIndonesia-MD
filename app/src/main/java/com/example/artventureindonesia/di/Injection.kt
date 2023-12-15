package com.example.artventureindonesia.di

import android.content.Context
import com.example.artventureindonesia.pref.UserPreference
import com.example.artventureindonesia.pref.dataStore
import com.example.artventureindonesia.remote.api.ApiConfig
import com.example.artventureindonesia.remote.repository.Repository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object Injection {
    fun provideRepository(context: Context): Repository {
        val pref = UserPreference.getInstance(context.dataStore)
        val user = runBlocking { pref.getSession().first() }
        val apiService = ApiConfig.getApiService(user.user_id)
        return Repository.getInstance(pref, apiService)

    }
}