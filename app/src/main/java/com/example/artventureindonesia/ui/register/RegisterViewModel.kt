package com.example.artventureindonesia.ui.register

import androidx.lifecycle.ViewModel
import com.example.artventureindonesia.remote.repository.Repository

class RegisterViewModel(private val repository: Repository) : ViewModel() {
    fun register(name: String, email: String, password: String) = repository.register(name, email, password)
}