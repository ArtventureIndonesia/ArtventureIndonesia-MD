package com.example.artventureindonesia.ui.reward

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.artventureindonesia.pref.UserModel
import com.example.artventureindonesia.remote.repository.Repository

class RewardViewModel(private val repository: Repository) : ViewModel() {
    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }
    fun getReward() = repository.getReward()
}