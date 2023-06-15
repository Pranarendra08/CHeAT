package com.example.cheat.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cheat.databinding.ActivitySplashScreenBinding
import com.example.cheat.ui.onboarding.OnBoardingActivity

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.apply {
            cheatLogo.alpha = 0f
            cheatLogo.animate().setDuration(1500).alpha(1f).withEndAction {
                startActivity(Intent(this@SplashScreenActivity, OnBoardingActivity::class.java))
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                finish()
            }
        }
    }
}