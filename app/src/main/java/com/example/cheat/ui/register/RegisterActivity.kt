package com.example.cheat.ui.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.cheat.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val registerViewModel: RegisterViewModel by viewModels { RegisterViewModelFactory(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.btnRegister.setOnClickListener {
            val username = binding.usernameInput.text.toString()
            val password = binding.passwordInput.text.toString()
            when {
                username.isEmpty() -> {
                    binding.usernameInput.error = "Please input your desirable username"
                }
                password.isEmpty() -> {
                    binding.passwordInput.error = "Please input your password"
                }
                else -> registerViewModel.createUserAccount(username, password)

            }
        }

        registerViewModel.toastSuccess.observe(this) {
            it.getContentIfNotHandled()?.let { toastText ->
                Toast.makeText(this@RegisterActivity, toastText, Toast.LENGTH_SHORT).show()
                finish()
            }
        }

        registerViewModel.toastFailed.observe(this) {
            it.getContentIfNotHandled()?.let { toastText ->
                Toast.makeText(this@RegisterActivity, toastText, Toast.LENGTH_SHORT).show()
            }
        }

        registerViewModel.isLoading.observe(this) {
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