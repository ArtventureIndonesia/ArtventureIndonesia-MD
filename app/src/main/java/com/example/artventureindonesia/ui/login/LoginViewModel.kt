package com.example.artventureindonesia.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artventureindonesia.pref.UserModel
import com.example.artventureindonesia.remote.repository.Repository
import com.example.artventureindonesia.remote.response.LoginResponse
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: Repository) : ViewModel() {

    fun login(name: String, email: String, password: String) = repository.login(name, email, password)

    fun saveSession(user: UserModel) {
        viewModelScope.launch {
            repository.saveSession(user)
        }
    }


}