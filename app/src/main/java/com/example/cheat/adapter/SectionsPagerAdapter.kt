package com.example.cheat.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.cheat.ui.onboarding.OnBoardingFirstFragment
import com.example.cheat.ui.onboarding.OnBoardingSecondFragment

class SectionsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount() = ITEM_COUNT

    override fun createFragment(position: Int): Fragment {
        val fragment: Fragment = when (position) {
            0 -> OnBoardingFirstFragment()
            1 -> OnBoardingSecondFragment()
            else -> throw IllegalArgumentException("Invalid Fragment Position + $position")
        }
        return fragment
    }

    private companion object {
        const val ITEM_COUNT = 2
    }
}