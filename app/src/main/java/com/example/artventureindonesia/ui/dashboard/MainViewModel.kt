package com.example.artventureindonesia.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.artventureindonesia.pref.UserModel
import com.example.artventureindonesia.remote.repository.Repository

class MainViewModel(private val repository: Repository) : ViewModel() {

    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }

    fun getMuseum() = repository.getMuseum()


}