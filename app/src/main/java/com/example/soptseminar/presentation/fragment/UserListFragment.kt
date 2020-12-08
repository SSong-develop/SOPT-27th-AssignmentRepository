package com.example.soptseminar.presentation.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.soptseminar.R
import com.example.soptseminar.data.remote.model.DummyUser
import com.example.soptseminar.databinding.FragmentUserListBinding
import com.example.soptseminar.presentation.adapter.DummyProfileAdapter
import com.example.soptseminar.presentation.viewmodel.MainViewModel

class UserListFragment : Fragment(){

    private lateinit var binding: FragmentUserListBinding
    private val viewModel: MainViewModel by activityViewModels()

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
        val dummyProfileAdapter = DummyProfileAdapter()
        setObserver(dummyProfileAdapter)
        viewModel.remoteDummy()
        setRecyclerView(dummyProfileAdapter)
        setTouchHelper(dummyProfileAdapter)

        binding.actBtn.setOnClickListener {
            changeLayoutManager()
        }

        return binding.root
    }

    private fun setObserver(dummyProfileAdapter: DummyProfileAdapter) {
        viewModel.dummyData.observe(viewLifecycleOwner){
            dummyProfileAdapter.dummyData = it.dummyUser as MutableList<DummyUser>
            dummyProfileAdapter.notifyDataSetChanged()
        }
    }

    private fun setRecyclerView(dummyProfileAdapter: DummyProfileAdapter) {
        binding.homeRecyclerView.apply {
            adapter = dummyProfileAdapter
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(DividerItemDecoration(requireContext(),LinearLayoutManager.VERTICAL))
        }
    }

    private fun changeLayoutManager() {
        if (binding.homeRecyclerView.layoutManager is GridLayoutManager) {
            binding.homeRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        } else {
            binding.homeRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    private fun setTouchHelper(dummyProfileAdapter: DummyProfileAdapter) {
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
                return dummyProfileAdapter.moveItem(viewHolder.adapterPosition, target.adapterPosition)
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                dummyProfileAdapter.removeItem(viewHolder.adapterPosition)
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
        itemTouchHelper.attachToRecyclerView(binding.homeRecyclerView)
    }

}