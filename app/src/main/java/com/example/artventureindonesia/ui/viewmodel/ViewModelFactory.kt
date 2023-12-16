package com.example.artventureindonesia.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.artventureindonesia.di.Injection
import com.example.artventureindonesia.remote.repository.Repository
import com.example.artventureindonesia.ui.dashboard.MainViewModel
import com.example.artventureindonesia.ui.detailtask.DetailTaskViewModel
import com.example.artventureindonesia.ui.login.LoginViewModel
import com.example.artventureindonesia.ui.register.RegisterViewModel
import com.example.artventureindonesia.ui.setting.SettingsViewModel
import com.example.artventureindonesia.ui.task.TaskViewModel
import com.example.artventureindonesia.ui.uploadtask.UploadTaskViewModel

class ViewModelFactory (private val repository: Repository) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(repository) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(repository) as T
            }
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> {
                RegisterViewModel(repository) as T
            }
            modelClass.isAssignableFrom(TaskViewModel::class.java) -> {
                TaskViewModel(repository) as T
            }
            modelClass.isAssignableFrom(DetailTaskViewModel::class.java) -> {
                DetailTaskViewModel(repository) as T
            }
            modelClass.isAssignableFrom(UploadTaskViewModel::class.java) -> {
                UploadTaskViewModel(repository) as T
            }
            modelClass.isAssignableFrom(SettingsViewModel::class.java) -> {
                SettingsViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
companion object {
    @Volatile
    private var INSTANCE: ViewModelFactory? = null
    @JvmStatic
    fun getInstance(context: Context): ViewModelFactory {
        if (INSTANCE == null) {
            synchronized(ViewModelFactory::class.java) {
                INSTANCE = ViewModelFactory(Injection.provideRepository(context))
            }
        }
        return INSTANCE as ViewModelFactory
    }
}
}