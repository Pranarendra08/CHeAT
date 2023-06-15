package com.example.cheat.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.cheat.MainActivity
import com.example.cheat.R
import com.example.cheat.databinding.ActivityLoginBinding
import com.example.cheat.pref.UserPreference
import com.example.cheat.ui.profile.ProfileFragment
import com.example.cheat.ui.register.RegisterActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels { LoginViewModelFactory(this) }
    private lateinit var userPreference: UserPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        userPreference = UserPreference(this)

        if (userPreference.getCookie() != null) {
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            finish()
        }

        binding.btnLogin.setOnClickListener {
            val username = binding.usernameInput.text.toString()
            val password = binding.passwordInput.text.toString()
            when {
                username.isEmpty() -> {
                    binding.usernameInput.error = "Input your username"
                }
                password.isEmpty() -> {
                    binding.passwordInput.error = "Input your password"
                }
                else -> {
                    loginViewModel.loginUserAccount(username, password)
                }
            }
        }

        binding.btnNewAccount.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }

        loginViewModel.toastSuccess.observe(this) {
            it.getContentIfNotHandled()?.let { toastText ->
                Toast.makeText(this@LoginActivity, toastText, Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                finish()
            }
        }

        loginViewModel.toastFailed.observe(this) {
            it.getContentIfNotHandled()?.let { toastText ->
                Toast.makeText(this@LoginActivity, toastText, Toast.LENGTH_SHORT).show()
            }
        }

        loginViewModel.isLoading.observe(this) {
            showLoading(it)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.registerProgressBar.visibility = View.VISIBLE
        } else {
            binding.registerProgressBar.visibility = View.GONE
        }
    }
}