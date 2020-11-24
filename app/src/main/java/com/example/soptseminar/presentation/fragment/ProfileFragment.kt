package com.example.soptseminar.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.soptseminar.R
import com.example.soptseminar.databinding.FragmentProfileBinding
import com.example.soptseminar.presentation.adapter.ProfileViewPagerAdapter

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var profileViewPager: ProfileViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        profileViewPager = ProfileViewPagerAdapter(parentFragmentManager)
        setAdapter()
        return binding.root
    }

    private fun setAdapter() {
        binding.viewpagerProfile.apply {
            adapter = profileViewPager
        }
        binding.tabProfile.apply {
            setupWithViewPager(binding.viewpagerProfile)
            getTabAt(0)?.text = "Info"
            getTabAt(1)?.text = "Other"
        }
    }

}