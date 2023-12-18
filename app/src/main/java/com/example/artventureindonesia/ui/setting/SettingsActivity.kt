package com.example.artventureindonesia.ui.setting

import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.example.artventureindonesia.R
import com.example.artventureindonesia.databinding.ActivitySettingsBinding
import com.example.artventureindonesia.pref.UserModel
import com.example.artventureindonesia.remote.result.Result
import com.example.artventureindonesia.ui.dashboard.MainActivity
import com.example.artventureindonesia.ui.login.LoginViewModel
import com.example.artventureindonesia.ui.opening.OpeningActivity
import com.example.artventureindonesia.ui.viewmodel.ViewModelFactory

class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding

    private val viewModel by viewModels<SettingsViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        viewModel.getSession().observe(this) { user ->
            if (!user.isLogin) {
                startActivity(Intent(this, OpeningActivity::class.java))
                finish()
            } else {

                val greeting = resources.getString(R.string.greeting, user.email)
                binding.tvEmail.text = greeting
            }
        }


        setupAction()
        setupView()
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
    private fun setupAction() {


        binding.cardLogout.setOnClickListener {
            showDialog()
        }
    }

    private fun logout() {
        viewModel.logout()
    }


    private fun showDialog() {
        val builder = AlertDialog.Builder(this)
        val title = getString(R.string.Logout)
        val confirmation = getString(R.string.account_logout)
        val yes = getString(R.string.yes)
        val no = getString(R.string.no)

        builder.setTitle(title)
        builder.setMessage(confirmation)

        builder.setPositiveButton(yes) { dialogInterface: DialogInterface, i: Int ->
            logout()
            finish()
        }

        builder.setNegativeButton(no) { dialogInterface: DialogInterface, i: Int ->
            dialogInterface.dismiss()
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

//    private fun showLoading(isLoading: Boolean){
//        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
//    }
}