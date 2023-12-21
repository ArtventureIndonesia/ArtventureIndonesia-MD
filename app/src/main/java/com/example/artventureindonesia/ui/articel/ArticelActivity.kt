package com.example.artventureindonesia.ui.articel

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.artventureindonesia.R
import com.example.artventureindonesia.databinding.ActivityArticelBinding
import com.example.artventureindonesia.databinding.ActivityRewardBinding
import com.example.artventureindonesia.remote.response.NewsDataItem
import com.example.artventureindonesia.remote.response.RewardDataItem
import com.example.artventureindonesia.remote.result.Result
import com.example.artventureindonesia.ui.adapter.ArticelAdapter
import com.example.artventureindonesia.ui.adapter.RewardAdapter
import com.example.artventureindonesia.ui.login.LoginActivity
import com.example.artventureindonesia.ui.reward.RewardActivity
import com.example.artventureindonesia.ui.reward.RewardViewModel
import com.example.artventureindonesia.ui.viewmodel.ViewModelFactory

class ArticelActivity : AppCompatActivity() {

    private val viewModel by viewModels<ArticelViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private lateinit var binding: ActivityArticelBinding

    private var user_id: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getSession()
        setupView()

        user_id = intent.getStringExtra(RewardActivity.ID)

        val layoutManager = LinearLayoutManager(this)
        binding.rvArticel.layoutManager = layoutManager

        val item = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvArticel.addItemDecoration(item)
    }

    private fun getSession() {
        viewModel.getSession().observe(this) { user ->

//            user_id?.let {
            if (!user.isLogin) {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            } else {
                viewModel.getArticel().observe(this) {result ->
                    if (result != null) {
                        when (result){
                            is Result.Loading -> {
                                showLoading(true)
                            }
                            is Result.Success -> {

                                showLoading(false)
                                val reward = result.data
                                if (reward != null) {
                                    rewardArticel(reward)
                                }
                            }

                            is Result.Error -> {
                                showLoading(false)
                                showToast(result.error)

                            }
                        }
                    }

                }
            }
//            }
        }
    }


    private fun rewardArticel(listArticel: List<NewsDataItem>) {
        val adapter = ArticelAdapter(this@ArticelActivity)
        adapter.submitList(listArticel)
        binding.rvArticel.adapter = adapter
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
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