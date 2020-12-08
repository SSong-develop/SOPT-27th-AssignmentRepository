package com.example.soptseminar.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.soptseminar.R
import com.example.soptseminar.data.remote.model.KakaoSearchDocument
import com.example.soptseminar.databinding.FragmentSettingBinding
import com.example.soptseminar.presentation.adapter.KakaoSearchAdapter
import com.example.soptseminar.presentation.viewmodel.MainViewModel

class SettingFragment : Fragment() {

    private lateinit var binding: FragmentSettingBinding
    private val viewModel : MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_setting, container, false)
        val kakaoSearchAdapter = KakaoSearchAdapter()
        setObserver()
        setSearchView()
        setAdapter(kakaoSearchAdapter)

        return binding.root
    }

    private fun setSearchView(){
        binding.settingSearchview.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                viewModel.setKeyword(p0!!)
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

        })
    }

    private fun setObserver(){
        viewModel.keyword.observe(viewLifecycleOwner){
            viewModel.search()
        }
    }

    private fun setAdapter(kakaoSearchAdapter: KakaoSearchAdapter){
        viewModel.searchData.observe(viewLifecycleOwner){
            kakaoSearchAdapter.searchData = it.document as MutableList<KakaoSearchDocument>
            kakaoSearchAdapter.notifyDataSetChanged()
        }
        binding.settingRecyclerview.apply {
            adapter = kakaoSearchAdapter
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(DividerItemDecoration(requireContext(),LinearLayoutManager.VERTICAL))
        }
    }


}