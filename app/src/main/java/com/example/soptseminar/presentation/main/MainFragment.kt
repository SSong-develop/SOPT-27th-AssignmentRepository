package com.example.soptseminar.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager.widget.ViewPager
import com.example.soptseminar.R
import com.example.soptseminar.databinding.FragmentMainBinding
import com.example.soptseminar.presentation.adapter.MainViewPagerAdapter
import com.example.soptseminar.presentation.viewmodel.MainViewModel
import kotlin.properties.Delegates

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var mainViewPagerAdapter: MainViewPagerAdapter

    private val viewModel : MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        mainViewPagerAdapter = MainViewPagerAdapter(parentFragmentManager)
        setViewpager()
        addViewPagerListener()
        setBottomNavigation()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

    private fun addViewPagerListener() {
        binding.viewpagerMain.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                binding.bottomNavigationMain.menu.getItem(position).isChecked = true
            }

            override fun onPageScrollStateChanged(state: Int) {}

        })
    }

    private fun setBottomNavigation() {
        binding.bottomNavigationMain.setOnNavigationItemSelectedListener {
            var index by Delegates.notNull<Int>()
            when (it.itemId) {
                R.id.menu_home -> index = 0
                R.id.menu_User -> index = 1
                R.id.menu_setting -> index = 2
            }
            binding.viewpagerMain.currentItem = index
            true
        }
    }

    private fun setViewpager() {
        binding.viewpagerMain.apply {
            adapter = mainViewPagerAdapter
        }
    }

}