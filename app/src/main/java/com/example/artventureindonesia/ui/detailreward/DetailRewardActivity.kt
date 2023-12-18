package com.example.artventureindonesia.ui.detailreward

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.example.artventureindonesia.databinding.ActivityDetailRewardBinding
import com.example.artventureindonesia.remote.response.DetailRewardResponse
import com.example.artventureindonesia.remote.response.DetailTaskResponse
import com.example.artventureindonesia.remote.response.RewardResponse
import com.example.artventureindonesia.remote.response.RewardsData
import com.example.artventureindonesia.remote.result.Result
import com.example.artventureindonesia.ui.uploadtask.UploadTaskActivity
import com.example.artventureindonesia.ui.viewmodel.ViewModelFactory

class DetailRewardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailRewardBinding

    private val viewModel by viewModels<DetailRewardViewModel> {
        ViewModelFactory.getInstance(this)
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailRewardBinding.inflate(layoutInflater)
        setContentView(binding.root)



        getRewardDetail()
    }

    private fun getRewardDetail() {
        val id = intent.getStringExtra(ID)

            viewModel.getRewardDetail(id!!).observe(this) { result ->
                if (result != null) {
                    when (result) {
                        is Result.Loading -> {
                            showLoading(true)
                        }

                        is Result.Success -> {
                            showLoading(false)

                            val rewardResponse = result.data.rewardsData
                            setDetailReward(rewardResponse)

                        }

                        is Result.Error -> {
                            showLoading(false)
                        }
                    }
                }

            }

    }

    private fun setDetailReward(reward: RewardsData) {
        binding.apply {
            Glide.with(binding.root.context)
                .load(reward.urlRewardImg)
                .into(binding.imgRewardDetail)
            tvDetailreward.text = reward.rewardName
            textDeskripsi.text = reward.rewardPoint.toString()
        }
    }


    private fun showToast(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    companion object {
        const val ID = "id"
    }
}