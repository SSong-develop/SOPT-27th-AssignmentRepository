package com.example.soptseminar.presentation.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.soptseminar.R
import com.example.soptseminar.databinding.FragmentSignUpBinding
import com.example.soptseminar.presentation.vm.SignUpViewModel
import com.example.soptseminar.utils.Validator

class SignUpFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentSignUpBinding>(inflater,R.layout.fragment_sign_up,container,false)
        val viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)
        binding.signupviewmodel = viewModel

        binding.signUpOkBtn.setOnClickListener {view : View ->

            val id = binding.signUpIdEdt.text.toString()
            val name = binding.signUpNameEdtxt.text.toString()
            val password = binding.signUpPassEdt.text.toString()
            if(Validator.isValidate(name,id,password)){
                viewModel.sharedPref.putStirngValue(requireContext(),"userName",name)
                viewModel.sharedPref.putStirngValue(requireContext(),"userId",id)
                viewModel.sharedPref.putStirngValue(requireContext(),"userPassword",password)
                view.findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToMainFragment(name,id,password))
            }else{
                Toast.makeText(requireContext().applicationContext,"empty",Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }


}