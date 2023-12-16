package com.example.artventureindonesia.ui.uploadtask

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.artventureindonesia.pref.UserModel
import com.example.artventureindonesia.remote.repository.Repository
import java.io.File

class UploadTaskViewModel(private val repository: Repository): ViewModel() {

    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }
    fun uploadImage(image: File, object_doc: String) =
        repository.uploadImage(image, object_doc)
}