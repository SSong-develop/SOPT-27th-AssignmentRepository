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
import com.example.soptseminar.data.remote.model.DummyUser
import com.example.soptseminar.databinding.FragmentSettingBinding
import com.example.soptseminar.presentation.adapter.DummyProfileAdapter
import com.example.soptseminar.presentation.viewmodel.MainViewModel

class SettingFragment : Fragment() {

    private lateinit var binding: FragmentSettingBinding
    private val viewModel : MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_setting, container, false)
        val dummyProfileAdapter = DummyProfileAdapter()
        setObserver(dummyProfileAdapter)
        viewModel.remoteDummy()
        setAdapter(dummyProfileAdapter)
        return binding.root
    }

    private fun setObserver(dummyProfileAdapter: DummyProfileAdapter) {
        viewModel.dummyData.observe(viewLifecycleOwner){
            dummyProfileAdapter.dummyData = it.dummyUser as MutableList<DummyUser>
            dummyProfileAdapter.notifyDataSetChanged()
        }
    }

    private fun setAdapter(dummyProfileAdapter: DummyProfileAdapter){
        binding.settingRecyclerview.apply {
            adapter = dummyProfileAdapter
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(DividerItemDecoration(requireContext(),LinearLayoutManager.VERTICAL))
        }
    }

}