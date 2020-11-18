package com.example.soptseminar.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.soptseminar.presentation.fragment.ProfileInfoFragment
import com.example.soptseminar.presentation.fragment.ProfileOtherFragment

class ProfileViewPagerAdapter(fm : FragmentManager) : FragmentStatePagerAdapter(fm,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> ProfileInfoFragment()
            else -> ProfileOtherFragment()
        }
    }
}