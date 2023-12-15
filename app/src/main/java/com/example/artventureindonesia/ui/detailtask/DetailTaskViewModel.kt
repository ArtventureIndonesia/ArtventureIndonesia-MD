package com.example.artventureindonesia.ui.detailtask

import androidx.lifecycle.ViewModel
import com.example.artventureindonesia.remote.repository.Repository

class DetailTaskViewModel (private val Repository: Repository): ViewModel() {

    fun getTaskDetail (id: String) = Repository.getDetailTask(id)

}