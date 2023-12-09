package com.example.artventureindonesia.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.artventureindonesia.di.Injection
import com.example.artventureindonesia.remote.repository.Repository

class ViewModelFactory private constructor(
    private val mRepository: Repository
): ViewModelProvider.NewInstanceFactory() {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//
//    }
//
//    companion object {
//        @Volatile
//        private var instance: ViewModelFactory? = null
//        fun getInstance(context: Context): ViewModelFactory =
//            instance ?: synchronized(this) {
//                instance ?: ViewModelFactory(
//                    Injection.provideUserRespository(context))
//            }.also { instance = it }
//    }
}