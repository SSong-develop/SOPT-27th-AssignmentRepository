package com.example.soptseminar.presentation.signin

import android.os.Bundle
import android.util.Log
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
import com.example.soptseminar.model.SignInUser
import com.example.soptseminar.presentation.viewmodel.MainViewModel
import com.example.soptseminar.utils.Injection

class SignInFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentSignInBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate<FragmentSignInBinding>(
            inflater,
            R.layout.fragment_sign_in,
            container,
            false
        )
        val loginController = LoginController(Injection.provideLoginDataStore(requireContext()))
        binding.mainviewmodel = viewModel

        autoLogin(loginController)
        setObserver(loginController)

        binding.loginBtn.setOnClickListener {
            if (isValidate(binding)) {
                viewModel.signIn(
                    SignInUser(
                        binding.mainEmailEdt.text.toString(),
                        binding.mainPassEdt.text.toString()
                    )
                )
            }
        }

        binding.signUpTxt.setOnClickListener {
            findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToSignUpFragment())
        }

        return binding.root
    }

    private fun setObserver(loginController: LoginController) {
        viewModel.userData.observe(viewLifecycleOwner){
            if(it.email.isNotBlank() && it.password.isNotBlank() && it.userName.isNotBlank()) {
                loginController.setAutoLogin()
                loginController.saveUserData(it)
                findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToMainFragment())
            }else {
                showToast("로그인에 실패했습니다.")
            }
        }
    }

    private fun autoLogin(loginController: LoginController) {
        if (loginController.getAutoLogin()) {
            showToast("자동로그인")
            viewModel.setUserData(loginController.fetchUserData())
            findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToMainFragment())
        }
    }

    private fun isValidate(binding: FragmentSignInBinding): Boolean {
        return binding.mainEmailEdt.text.isNotBlank() && binding.mainPassEdt.text.isNotBlank()
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

}