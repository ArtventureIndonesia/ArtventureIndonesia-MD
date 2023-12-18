package com.example.artventureindonesia.pref

data class UserModel(
    val email: String,
    val user_id: String,
    val isLogin: Boolean = false,
    val point: Int
)