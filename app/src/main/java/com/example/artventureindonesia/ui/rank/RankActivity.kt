package com.example.artventureindonesia.ui.rank

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
import com.example.artventureindonesia.databinding.ActivityMainBinding
import com.example.artventureindonesia.databinding.ActivityRankBinding
import com.example.artventureindonesia.remote.response.MuseumDataItem
import com.example.artventureindonesia.remote.response.UserDataItem
import com.example.artventureindonesia.remote.result.Result
import com.example.artventureindonesia.ui.adapter.PlaceAdapter
import com.example.artventureindonesia.ui.adapter.RankAdapter
import com.example.artventureindonesia.ui.dashboard.MainActivity
import com.example.artventureindonesia.ui.dashboard.MainViewModel
import com.example.artventureindonesia.ui.opening.OpeningActivity
import com.example.artventureindonesia.ui.setting.SettingsActivity
import com.example.artventureindonesia.ui.viewmodel.ViewModelFactory

class RankActivity : AppCompatActivity() {

    private val viewModel by viewModels<RankViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private lateinit var binding: ActivityRankBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRankBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener{
            val intentMain = Intent(this@RankActivity, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intentMain)
        }

        setupView()
        getSession()

        val layoutManager = LinearLayoutManager(this)
        binding.rvRank.layoutManager = layoutManager

        val item = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvRank.addItemDecoration(item)
    }

    private fun getSession() {
        viewModel.getSession().observe(this) { user ->
            if (!user.isLogin) {
                startActivity(Intent(this, OpeningActivity::class.java))
                finish()
            } else {
                viewModel.getRank().observe(this) {result ->

                    if (result != null) {
                        when (result){
                            is Result.Loading -> {
                                showLoading(true)
                            }

                            is Result.Success -> {
                                showLoading(false)

                                val place = result.data
                                if (place != null) {
                                    rankAdapter(place)
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

        }

    }


    private fun rankAdapter(listRank: List<UserDataItem>) {
        val adapter = RankAdapter(this@RankActivity)
        adapter.submitList(listRank)
        binding.rvRank.adapter = adapter
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



}