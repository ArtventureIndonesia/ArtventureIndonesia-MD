package com.example.artventureindonesia.ui.dashboard

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.artventureindonesia.R
import com.example.artventureindonesia.databinding.ActivityMainBinding
import com.example.artventureindonesia.remote.response.MuseumDataItem
import com.example.artventureindonesia.ui.adapter.PlaceAdapter
import com.example.artventureindonesia.ui.login.LoginActivity
import com.example.artventureindonesia.ui.opening.OpeningActivity
import com.example.artventureindonesia.ui.viewmodel.ViewModelFactory
import com.example.artventureindonesia.remote.result.Result
import com.example.artventureindonesia.ui.detailtask.DetailTaskActivity
import com.example.artventureindonesia.ui.register.RegisterActivity
import com.example.artventureindonesia.ui.reward.RewardActivity
import com.example.artventureindonesia.ui.setting.SettingsActivity

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getSession()
        setupView()

        val layoutManager = LinearLayoutManager(this)
        binding.rvPlace.layoutManager = layoutManager

        val item = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvPlace.addItemDecoration(item)

        binding.btnSettings.setOnClickListener{
            val intentSetting = Intent(this@MainActivity, SettingsActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intentSetting)
        }


    }

    private fun getSession() {
        viewModel.getSession().observe(this) { user ->
                val name = resources.getString(R.string.greeting, user.email)
                binding.tvHello.text = name
            if (!user.isLogin) {
                startActivity(Intent(this, OpeningActivity::class.java))
                finish()
            } else {
                    viewModel.getMuseum().observe(this) {result ->

                        if (result != null) {
                            when (result){
                                is Result.Loading -> {
                                    showLoading(true)
                                }

                                is Result.Success -> {
                                    showLoading(false)

                                    val place = result.data
                                    if (place != null) {
                                        placeAdapter(place)
                                    }
                                }

                                is Result.Error -> {
                                    showLoading(false)
                                    showToast(result.error)

                                }
                            }
                        }

                }

                binding.tvPoint.text = user.point.toString()
            }

        }
    }

    private fun placeAdapter(listStory: List<MuseumDataItem>) {
        val adapter = PlaceAdapter(this@MainActivity)
        adapter.submitList(listStory)
        binding.rvPlace.adapter = adapter
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