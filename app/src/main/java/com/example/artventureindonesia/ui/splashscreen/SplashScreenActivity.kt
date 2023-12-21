package com.example.artventureindonesia.ui.splashscreen

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.WindowInsets
import android.view.WindowManager
import com.example.artventureindonesia.R
import com.example.artventureindonesia.ui.dashboard.MainActivity
import com.example.artventureindonesia.ui.detailtask.DetailTaskActivity
import com.example.artventureindonesia.ui.login.LoginActivity
import com.example.artventureindonesia.ui.opening.OpeningActivity
import com.example.artventureindonesia.ui.rank.RankActivity
import com.example.artventureindonesia.ui.reward.RewardActivity
import com.example.artventureindonesia.ui.task.TaskActivity
import com.example.artventureindonesia.ui.uploadtask.UploadTaskActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val countDownTimer = object : CountDownTimer(2000L, 1000){
            override fun onTick(millisUnitilFinished: Long) {
            }

            override fun onFinish() {
                navigateToMainActivity()
            }
        }
        countDownTimer.start()
        setupView()
    }
    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
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
}