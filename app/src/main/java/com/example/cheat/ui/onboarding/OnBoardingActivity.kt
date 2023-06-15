package com.example.cheat.ui.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.cheat.MainActivity
import com.example.cheat.adapter.SectionsPagerAdapter
import com.example.cheat.databinding.ActivityOnBoardingBinding
import com.example.cheat.ui.login.LoginActivity

class OnBoardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnBoardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        val viewPager: ViewPager2 = binding.onBoardingViewPager
        val wormDotsIndicator = binding.wormDotsIndicator
        viewPager.adapter = sectionsPagerAdapter
        wormDotsIndicator.attachTo(viewPager)

        binding.startButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}