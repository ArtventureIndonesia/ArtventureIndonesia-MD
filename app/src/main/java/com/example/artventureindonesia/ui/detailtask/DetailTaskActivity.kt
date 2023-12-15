package com.example.artventureindonesia.ui.detailtask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.artventureindonesia.databinding.ActivityDetailTaskBinding
import com.example.artventureindonesia.remote.response.TaskData
import com.example.artventureindonesia.ui.viewmodel.ViewModelFactory
import com.example.artventureindonesia.remote.result.Result

class DetailTaskActivity : AppCompatActivity() {


    lateinit var binding: ActivityDetailTaskBinding

    private val viewModel by viewModels<DetailTaskViewModel> {
        ViewModelFactory.getInstance(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getTaskDetail()
    }

    private fun getTaskDetail() {
        val id = intent.getStringExtra(ID)?:"null"
        viewModel.getTaskDetail(id).observe(this) {result ->
            if (result != null) {
                when (result) {
                    is Result.Loading -> {
                        showLoading(true)
                    }

                    is Result.Success -> {
                        showLoading(false)
                        showToast(result.data.message)
                        val detailResponse = result.data.taskData
                        setDetailTask(detailResponse!!)
                    }

                    is Result.Error -> {
                        showLoading(false)
                    }
                }
            }

        }
    }

    private fun setDetailTask(task: TaskData) {
        binding.apply {

//            Glide.with(this@DetailTaskActivity)
//                .load(story.photoUrl)
//                .into(
//                    imageDetail
//                )
            tvJudulDetailtask.text = task.objectName
            tvDeskripsidetail.text = task.objectDescription
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