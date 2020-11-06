package com.example.soptseminar.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.soptseminar.R
import com.example.soptseminar.databinding.FragmentMainBinding
import com.example.soptseminar.presentation.vm.MainViewModel

class MainFragment : Fragment() {

    private lateinit var viewModel : MainViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentMainBinding>(inflater,R.layout.fragment_main,container,false)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.mainviewmodel = viewModel

        binding.loginBtn.setOnClickListener {view : View ->
            if(login(binding.mainIdEdt.text.toString(), binding.mainPassEdt.text.toString()))
                view.findNavController().navigate(MainFragmentDirections.actionMainFragmentToHomeFragment())
            else
                Toast.makeText(requireContext(),"로그인 실패",Toast.LENGTH_SHORT).show()
        }

        binding.signUpTxt.setOnClickListener {view : View ->
            view.findNavController().navigate(MainFragmentDirections.actionMainFragmentToSignUpFragment())
        }

        arguments?.let {
            MainFragmentArgs.fromBundle(it).let {
                binding.mainIdEdt.setText(it.userId)
                binding.mainPassEdt.setText(it.userPassword)
            }
        }

        return binding.root
    }

    fun login(id : String , password : String): Boolean {
        if(id.equals(viewModel.sharedPref.getStringValue(requireContext(),"userId"))&&
                password.equals(viewModel.sharedPref.getStringValue(requireContext(),"userPassword"))) return true
        return false
    }

}