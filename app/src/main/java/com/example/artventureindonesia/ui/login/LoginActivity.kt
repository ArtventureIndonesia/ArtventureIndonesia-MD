package com.example.artventureindonesia.ui.login

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.example.artventureindonesia.R
import com.example.artventureindonesia.databinding.ActivityLoginBinding
import com.example.artventureindonesia.pref.UserModel
import com.example.artventureindonesia.ui.costumeview.EmailCostumeView
import com.example.artventureindonesia.ui.costumeview.PasswordCostumeView
import com.example.artventureindonesia.ui.register.RegisterActivity
import com.example.artventureindonesia.ui.viewmodel.ViewModelFactory
import com.example.artventureindonesia.remote.result.Result
import com.example.artventureindonesia.ui.dashboard.MainActivity

class LoginActivity : AppCompatActivity() {

    private val viewModel by viewModels<LoginViewModel> {
        ViewModelFactory.getInstance(this)
    }

    private lateinit var passwordEditText: PasswordCostumeView
    private lateinit var loginButton: Button
    private lateinit var emailEditText: EmailCostumeView

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        emailEditText = binding.edLoginEmail
        passwordEditText = binding.edLoginPassword
        loginButton = binding.buttonLogin

        binding.tvRegister.setOnClickListener {
            val intent = Intent (this@LoginActivity, RegisterActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }

        //validasi email
        emailEditText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //do nothing
            }

            override fun onTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                setMyButtonEnable()
            }

            override fun afterTextChanged(s: Editable?) {
                //do nothing
            }

        })

        //validasi password
        setMyButtonEnable()

        passwordEditText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //do nothing
            }

            override fun onTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                setMyButtonEnable()
            }

            override fun afterTextChanged(s: Editable?) {
                //do nothing
            }

        })

        setupView()
        setupAction()
        playAnimator()
    }



    private fun setMyButtonEnable() {
        val password = passwordEditText.text
        val email = emailEditText.text
        val validEmail = android.util.Patterns.EMAIL_ADDRESS.matcher(email.toString()).matches()

        loginButton.isEnabled = password != null && password.toString().length >= 8 && email != null && validEmail
        if (!loginButton.isEnabled){
            binding.buttonLogin.alpha = 0.3f
        } else {
            binding.buttonLogin.alpha = 1f
        }
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
        binding.buttonLogin.setOnClickListener {
            val name = binding.edLoginEmail.text.toString()
            val email = binding.edLoginEmail.text.toString()
            val password = binding.edLoginPassword.text.toString()
            viewModel.login(name, email, password).observe(this) { result ->
                if (result != null) {
                    when (result) {
                        is Result.Loading -> {
                            showLoading(true)
                        }

                        is Result.Success -> {
                            showLoading(false)
                            val user = UserModel(
                                user_id = result.data.userData.userId,
                                email = email,
                                point = result.data.userData.userPoints,
                            )
                            viewModel.saveSession(user)
                            alertDialog()
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
    private fun alertDialog() {
        AlertDialog.Builder(this).apply {
            val title = getString(R.string.congrats)
            val accountCreated = getString(R.string.account_created)
            val next = getString(R.string.next)

            setTitle(title)
            setMessage(accountCreated)
            setPositiveButton(next) { _, _ ->
                val intent = Intent(context, MainActivity::class.java)

                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                finish()
            }
            create()
            show()
        }
    }

    private fun showToast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading(isLoading: Boolean){
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun playAnimator() {
        ObjectAnimator.ofFloat(binding.imageView, View.TRANSLATION_X, -30f, 30f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        val image = ObjectAnimator.ofFloat(binding.imageView, View.ALPHA, 1f).setDuration(300)
        val welcomebck = ObjectAnimator.ofFloat(binding.tvWelcomeBack, View.ALPHA, 1f).setDuration(300)
        val emailEdt =
            ObjectAnimator.ofFloat(binding.layLoginEmail, View.ALPHA, 1f).setDuration(300)
        val passEdt =
            ObjectAnimator.ofFloat(binding.layLoginPassword, View.ALPHA, 1f).setDuration(300)
        val signBtn = ObjectAnimator.ofFloat(binding.buttonLogin, View.ALPHA, 1f).setDuration(300)
        val tvRegister = ObjectAnimator.ofFloat(binding.tvRegister, View.ALPHA, 1f).setDuration(300)

        val together = AnimatorSet().apply {
            playSequentially(image,welcomebck, emailEdt, passEdt, signBtn, tvRegister)
            startDelay = 100
        }

        AnimatorSet().apply {
            playSequentially(together, signBtn)
            start()
        }

    }
}