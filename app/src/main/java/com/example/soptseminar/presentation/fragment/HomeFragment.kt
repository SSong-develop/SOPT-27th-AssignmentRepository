package com.example.soptseminar.presentation.fragment

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
import com.example.soptseminar.presentation.model.ProfileData
import com.example.soptseminar.utils.MakeDummy
import kotlinx.android.synthetic.main.activity_home.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
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
}