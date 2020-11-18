package com.example.soptseminar.presentation.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.soptseminar.R
import com.example.soptseminar.databinding.FragmentSignInBinding
import com.example.soptseminar.databinding.FragmentSignUpBinding
import com.example.soptseminar.presentation.viewmodel.MainViewModel

class SignUpFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentSignUpBinding>(
            inflater,
            R.layout.fragment_sign_up,
            container,
            false
        )

        binding.signUpOkBtn.setOnClickListener {
            signUp(
                binding.signUpNameEdtxt.text.toString(),
                binding.signUpIdEdt.text.toString(),
                binding.signUpPassEdt.text.toString()
            )
            moveSignInPage(binding.signUpIdEdt.text.toString(),binding.signUpPassEdt.text.toString())
        }

        return binding.root
    }

    private fun signUp(name: String, id: String, password: String) {
        if (name.isNotBlank() && id.isNotBlank() && password.isNotBlank()) {
            saveUser(name,id,password)
        } else {
            Toast.makeText(requireContext(), "회원가입 실패", Toast.LENGTH_SHORT).show()
        }
    }

    // boiler plate , make SharedPref more clean and effective
    fun saveUser(name: String, id: String, password: String){
        viewModel.sharedPref.putStirngValue(requireContext(), "userName", name)
        viewModel.sharedPref.putStirngValue(requireContext(), "userId", id)
        viewModel.sharedPref.putStirngValue(requireContext(), "userPassword", password)
    }

    fun moveSignInPage(id : String , password : String){
        findNavController().previousBackStackEntry?.savedStateHandle?.set("userId",id)
        findNavController().previousBackStackEntry?.savedStateHandle?.set("userPassword",password)
        findNavController().popBackStack()
    }
}
