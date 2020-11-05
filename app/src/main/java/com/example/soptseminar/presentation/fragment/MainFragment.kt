package com.example.soptseminar.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.soptseminar.R
import com.example.soptseminar.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentMainBinding>(inflater,R.layout.fragment_main,container,false)
        binding.loginBtn.setOnClickListener {view : View ->
            view.findNavController().navigate(MainFragmentDirections.actionMainFragmentToHomeFragment())
        }
        binding.signUpTxt.setOnClickListener {view : View ->
            view.findNavController().navigate(MainFragmentDirections.actionMainFragmentToSignUpFragment())
        }
        var args = arguments?.let {
            MainFragmentArgs.fromBundle(it).let {
                binding.mainIdEdt.setText(it.userId)
                binding.mainPassEdt.setText(it.userPassword)
            }
        }

        return binding.root
    }
}