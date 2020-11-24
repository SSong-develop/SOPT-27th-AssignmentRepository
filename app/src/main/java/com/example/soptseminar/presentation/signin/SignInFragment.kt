package com.example.soptseminar.presentation.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.soptseminar.R
import com.example.soptseminar.data.local.sharedpreference.LoginController
import com.example.soptseminar.databinding.FragmentSignInBinding
import com.example.soptseminar.presentation.viewmodel.MainViewModel
import com.example.soptseminar.utils.Injection

class SignInFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentSignInBinding>(
            inflater,
            R.layout.fragment_sign_in,
            container,
            false
        )
        val loginController = LoginController(Injection.provideLoginDataStore(requireContext()))
        binding.mainviewmodel = viewModel
        autoLogin(loginController)

        binding.loginBtn.setOnClickListener {
            if(isValidate(binding)){
                viewModel.signIn()
                loginController.setAutoLoginKey()
                findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToMainFragment())
            }
        }

        binding.signUpTxt.setOnClickListener {
            findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToSignUpFragment())
        }

        return binding.root
    }

    private fun autoLogin(loginController: LoginController) {
        if (loginController.getAutoLoginKey()) {
            Toast.makeText(requireContext(), "자동 로그인", Toast.LENGTH_SHORT).show()
            findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToMainFragment())
        }
    }

    private fun isValidate(binding: FragmentSignInBinding) : Boolean{
        return binding.mainEmailEdt.text.isNotBlank() && binding.mainPassEdt.text.isNotBlank()
    }

}