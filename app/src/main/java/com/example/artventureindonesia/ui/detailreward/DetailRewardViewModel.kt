package com.example.artventureindonesia.ui.detailreward

import androidx.lifecycle.ViewModel
import com.example.artventureindonesia.remote.repository.Repository

class DetailRewardViewModel (private val Repository: Repository): ViewModel() {

    fun getRewardDetail (id: String) = Repository.getDetailReward(id)
}