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
import com.example.soptseminar.data.sharedpreference.LoginController
import com.example.soptseminar.databinding.FragmentSignInBinding
import com.example.soptseminar.presentation.model.User
import com.example.soptseminar.presentation.viewmodel.MainViewModel
import com.example.soptseminar.utils.Injection

class SignInFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()

    private lateinit var binding: FragmentSignInBinding
    private lateinit var loginController: LoginController

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
        loginController = LoginController(Injection.provideLoginDataStore(requireContext()))

        autoLogin()

        binding.loginBtn.setOnClickListener {
            val user = User(
                loginController.getUserName(),
                binding.mainIdEdt.text.toString(),
                binding.mainPassEdt.text.toString()
            )
            login(user)
        }

        binding.signUpTxt.setOnClickListener {
            findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToSignUpFragment())
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val navController = findNavController()
        navController.currentBackStackEntry?.savedStateHandle?.getLiveData<String>("userId")
            ?.observe(viewLifecycleOwner) {
                viewModel.userId.value = it
            }
        navController.currentBackStackEntry?.savedStateHandle?.getLiveData<String>("userPassword")
            ?.observe(viewLifecycleOwner) {
                viewModel.userPassword.value = it
            }
    }

    fun autoLogin(){
        if (loginController.getAutoLoginKey()) {
            Toast.makeText(requireContext(), "자동 로그인", Toast.LENGTH_SHORT).show()
            findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToMainFragment())
        }
    }

    fun login(user : User){
        if (loginController.signIn(user)) {
            loginController.setAutoLoginKey()
            findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToMainFragment())
        } else {
            Toast.makeText(requireContext(), "로그인 실패", Toast.LENGTH_SHORT).show()
        }
    }

}