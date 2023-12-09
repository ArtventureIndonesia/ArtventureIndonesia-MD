package com.example.artventureindonesia.ui.uploadtask

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import com.example.artventureindonesia.R
import com.example.artventureindonesia.databinding.ActivityUploadTaskBinding
//import com.example.artventureindonesia.ui.viewmodel.ViewModelFactory
import com.example.artventureindonesia.utils.reduceFileImage
import com.example.artventureindonesia.utils.uriToFile
import com.google.android.material.snackbar.Snackbar

class UploadTaskActivity : AppCompatActivity() {
//    private lateinit var binding: ActivityUploadTaskBinding
//    private var currentImageUri: Uri? = null
//
//    private val uploadTaskViewModel by viewModels<UploadTaskViewModel> {
//        ViewModelFactory.getInstance(this)
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityUploadTaskBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        binding.btnCamera.setOnClickListener {
//            startCamera()
//        }
//
//        binding.btnGallery.setOnClickListener {
//            startGallery()
//        }
//
//        uploadTaskViewModel.isLoading.observe(this) {
//            showLoading(it)
//        }
//
//        uploadTaskViewModel.snackbar.observe(this) {
//            it.getContentIfNotHandled()?.let { SnackBarText ->
//                Snackbar.make(
//                    window.decorView.rootView,
//                    SnackBarText,
//                    Snackbar.LENGTH_SHORT
//                ).show()
//            }
//        }
//
//        binding.btnUpload.setOnClickListener {
//            currentImageUri?.let { uri ->
//                val imageFile = uriToFile(uri, this).reduceFileImage()
////                val description = binding.deskripsi.text.toString()
//
//                uploadTaskViewModel.uploadImage(imageFile).observe(this) { result ->
//
//                    val intent = Intent(this, ListStoryActivity::class.java)
//                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
//                    startActivity(intent)
//                    finish()
//
//                }
//            } ?: showToast(getString(R.string.empty_image_warning))
//        }
//    }
//
//    private val launcherIntentCamera = registerForActivityResult(
//        ActivityResultContracts.TakePicture()
//    ) { isSuccess ->
//        if (isSuccess) {
//            showImage()
//        }
//    }
//
//    private fun startCamera() {
//        currentImageUri = getImageUri(this)
//        launcherIntentCamera.launch(currentImageUri)
//    }
//
//    private val launchGallery = registerForActivityResult(
//        ActivityResultContracts.PickVisualMedia()
//    ) { uri: Uri? ->
//        if (uri != null) {
//            currentImageUri = uri
//            showImage()
//        } else {
//            Log.d("Photo Picker", "No Media Selected")
//        }
//    }
//
//    private fun startGallery() {
//        launchGallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
//    }
//
//    private fun showImage() {
//        currentImageUri?.let {
//            binding.imageView.setImageURI(it)
//        }
//    }
//
//    private fun showLoading(isLoading: Boolean) {
//        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
//    }
//
//    private fun showToast(message: String) {
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
//    }
}