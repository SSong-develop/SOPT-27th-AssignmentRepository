package com.example.soptseminar.presentation.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.soptseminar.R
import com.example.soptseminar.databinding.FragmentUserListBinding
import com.example.soptseminar.model.ProfileData
import com.example.soptseminar.presentation.adapter.OnItemClickListener
import com.example.soptseminar.presentation.adapter.ProfileAdapter
import com.example.soptseminar.presentation.viewmodel.MainViewModel

class UserListFragment : Fragment(), OnItemClickListener {

    private lateinit var binding: FragmentUserListBinding
    private val viewModel: MainViewModel by activityViewModels()

    private lateinit var profileAdapter: ProfileAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate<FragmentUserListBinding>(
            inflater,
            R.layout.fragment_user_list,
            container,
            false
        )
        profileAdapter = ProfileAdapter(this)

        binding.actBtn.setOnClickListener {
            changeLayoutManager()
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setDummy()
        setRecyclerView(binding.homeRecyclerView)
        setTouchHelper(binding.homeRecyclerView)
    }

    override fun onItemClicked(profileData: ProfileData) {
        findNavController().navigate(R.id.detailFragment)
    }

    private fun setDummy() {
        profileAdapter.data = viewModel.dummy
        profileAdapter.notifyDataSetChanged()
    }

    private fun setRecyclerView(recyclerView: RecyclerView) {
        recyclerView.apply {
            adapter = profileAdapter
            layoutManager = LinearLayoutManager(requireContext().applicationContext)
        }
    }

    private fun changeLayoutManager() {
        if (binding.homeRecyclerView.layoutManager is GridLayoutManager) {
            binding.homeRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        } else {
            binding.homeRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
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