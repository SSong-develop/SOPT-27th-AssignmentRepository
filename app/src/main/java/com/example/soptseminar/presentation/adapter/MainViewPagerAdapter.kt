package com.example.soptseminar.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.soptseminar.presentation.fragment.ProfileFragment
import com.example.soptseminar.presentation.fragment.SettingFragment
import com.example.soptseminar.presentation.fragment.UserListFragment

class MainViewPagerAdapter(fm: FragmentManager) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getCount(): Int = 3

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> ProfileFragment()
            1 -> UserListFragment()
            else -> SettingFragment()
        }
    }
}