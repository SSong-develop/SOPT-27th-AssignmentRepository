package com.example.soptseminar.presentation.fragment

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.soptseminar.R
import com.example.soptseminar.databinding.FragmentHomeBinding
import com.example.soptseminar.presentation.adapter.ProfileAdapter
import com.example.soptseminar.utils.MakeDummy

class HomeFragment : Fragment() {

    private lateinit var profileAdapter : ProfileAdapter

    private var isChecked : Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(inflater,R.layout.fragment_home,container,false)
        profileAdapter = ProfileAdapter(requireContext().applicationContext)

        setDummy(profileAdapter)
        setRecyclerView(binding.homeRecyclerView)
        setTouchHelper(binding.homeRecyclerView)
        profileAdapter.notifyDataSetChanged()

        binding.actBtn.setOnClickListener {
            changeLayoutManager(binding.homeRecyclerView)
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
            if(isChecked == false){
                layoutManager = GridLayoutManager(requireContext().applicationContext,2)
                isChecked = true
            } else {
                layoutManager = LinearLayoutManager(requireContext().applicationContext)
                isChecked = false
            }
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