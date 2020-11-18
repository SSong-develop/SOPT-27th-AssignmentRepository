package com.example.soptseminar.presentation.signin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.soptseminar.R
import com.example.soptseminar.databinding.FragmentSignInBinding
import com.example.soptseminar.presentation.viewmodel.MainViewModel

class SignInFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()

    private lateinit var binding: FragmentSignInBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentSignInBinding>(
            inflater,
            R.layout.fragment_sign_in,
            container,
            false
        )
        binding.viewModelMain = viewModel
        val navController = findNavController()

        if(viewModel.sharedPref.getBooleanValue(requireContext(), "auto") == true){
            Toast.makeText(requireContext(),"자동 로그인",Toast.LENGTH_SHORT).show()
            navController.navigate(SignInFragmentDirections.actionSignInFragmentToMainFragment())
        }

        binding.loginBtn.setOnClickListener {
            if (login(binding.mainIdEdt.text.toString(), binding.mainPassEdt.text.toString())) {
                accessAutoLogin()
                navController.navigate(SignInFragmentDirections.actionSignInFragmentToMainFragment())
            } else
                Toast.makeText(requireContext(), "로그인 실패", Toast.LENGTH_SHORT).show()
        }

        binding.signUpTxt.setOnClickListener {
            navController.navigate(SignInFragmentDirections.actionSignInFragmentToSignUpFragment())
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val navController = findNavController()

        // boiler plate
        navController.currentBackStackEntry?.savedStateHandle?.getLiveData<String>("userId")?.observe(viewLifecycleOwner){
            viewModel.userId.value = it
        }
        navController.currentBackStackEntry?.savedStateHandle?.getLiveData<String>("userPassword")?.observe(viewLifecycleOwner){
            viewModel.userPassword.value = it
        }
    }

    fun login(id: String, password: String): Boolean {
        return id == viewModel.sharedPref.getStringValue(requireContext(), "userId") &&
                password == viewModel.sharedPref.getStringValue(requireContext(), "userPassword")
    }

    fun accessAutoLogin(){
        viewModel.sharedPref.putBooleanValue(requireContext(), "auto", true)
    }

}