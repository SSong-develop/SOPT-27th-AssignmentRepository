package com.example.soptseminar.presentation.fragment

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.soptseminar.R
import com.example.soptseminar.databinding.FragmentHomeBinding
import com.example.soptseminar.presentation.adapter.ProfileAdapter
import com.example.soptseminar.presentation.vm.HomeViewModel
import com.example.soptseminar.utils.MakeDummy

class HomeFragment : Fragment() {

    private lateinit var profileAdapter : ProfileAdapter

    private lateinit var homeViewModel : HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(inflater,R.layout.fragment_home,container,false)
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        binding.viewModel = homeViewModel

        profileAdapter = ProfileAdapter(ProfileAdapter.ProfileListener{
            userId -> view?.findNavController()?.navigate(R.id.action_homeFragment_to_detailFragment)
        })

        setDummy(profileAdapter)
        setRecyclerView(binding.homeRecyclerView)
        setTouchHelper(binding.homeRecyclerView)
        changeLayoutManager(binding.homeRecyclerView)

        binding.actBtn.setOnClickListener {
            if(homeViewModel.isChecked.value == true){
                homeViewModel.setLinear()
            }else{
                homeViewModel.setGrid()
            }
        }

        return binding.root
    }

    fun setDummy(profileAdapter: ProfileAdapter){
        MakeDummy.makeDummy(profileAdapter)
    }

    fun setRecyclerView(recyclerView : RecyclerView){
        recyclerView.apply {
            adapter = profileAdapter
            layoutManager = LinearLayoutManager(requireContext().applicationContext)
        }
    }

    fun changeLayoutManager(recyclerView: RecyclerView){
        recyclerView.apply {
            homeViewModel.isChecked.observe(viewLifecycleOwner, Observer {
                if(it == true){
                    layoutManager = LinearLayoutManager(requireContext())
                }else{
                    layoutManager = GridLayoutManager(requireContext(),2)
                }
            })
        }
    }

    private fun setTouchHelper(recyclerView: RecyclerView) {
        val itemTouchHelper = androidx.recyclerview.widget.ItemTouchHelper(object :
            androidx.recyclerview.widget.ItemTouchHelper.SimpleCallback(
                androidx.recyclerview.widget.ItemTouchHelper.UP or androidx.recyclerview.widget.ItemTouchHelper.DOWN,
                androidx.recyclerview.widget.ItemTouchHelper.START or androidx.recyclerview.widget.ItemTouchHelper.END
            ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return profileAdapter.moveItem(viewHolder.adapterPosition, target.adapterPosition)
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                profileAdapter.removeItem(viewHolder.adapterPosition)
            }

            override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
                super.onSelectedChanged(viewHolder, actionState)
                if (actionState == androidx.recyclerview.widget.ItemTouchHelper.ACTION_STATE_DRAG) {
                    viewHolder?.itemView?.setBackgroundColor(Color.LTGRAY)
                }
            }

            override fun clearView(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ) {
                super.clearView(recyclerView, viewHolder)
                viewHolder.itemView.setBackgroundColor(Color.WHITE)
            }
        })
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }
}