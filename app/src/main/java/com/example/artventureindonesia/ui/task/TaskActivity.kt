package com.example.artventureindonesia.ui.task

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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.artventureindonesia.R
import com.example.artventureindonesia.databinding.ActivityMainBinding
import com.example.artventureindonesia.databinding.ActivityTaskBinding
import com.example.artventureindonesia.remote.response.MuseumDataItem
import com.example.artventureindonesia.remote.response.TaskDataItem
import com.example.artventureindonesia.remote.result.Result
import com.example.artventureindonesia.ui.adapter.PlaceAdapter
import com.example.artventureindonesia.ui.adapter.TaskAdapter
import com.example.artventureindonesia.ui.dashboard.MainViewModel
import com.example.artventureindonesia.ui.login.LoginActivity
import com.example.artventureindonesia.ui.viewmodel.ViewModelFactory

class TaskActivity : AppCompatActivity() {

    private val viewModel by viewModels<TaskViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private lateinit var binding: ActivityTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getSession()
        setupView()

        val layoutManager = LinearLayoutManager(this)
        binding.rvTask.layoutManager = layoutManager

        val item = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvTask.addItemDecoration(item)
    }

    private fun getSession() {
        viewModel.getSession().observe(this) { user ->
            if (!user.isLogin) {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            } else {
                val id = intent.getStringExtra(ID) ?:"null"
                viewModel.getTask().observe(this) {result ->
                    if (result != null) {
                        when (result){
                            is Result.Loading -> {
                                showLoading(true)
                            }
                            is Result.Success -> {
                                showLoading(false)
                                val place = result.data
                                place?.let { taskAdapter(it) }
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

    private fun taskAdapter(listTask: List<TaskDataItem>) {
        val adapter = TaskAdapter(this@TaskActivity)
        adapter.submitList(listTask)
        binding.rvTask.adapter = adapter
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