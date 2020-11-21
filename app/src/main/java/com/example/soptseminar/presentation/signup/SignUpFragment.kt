package com.example.soptseminar.presentation.signup

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
import com.example.soptseminar.databinding.FragmentSignUpBinding
import com.example.soptseminar.presentation.model.User
import com.example.soptseminar.presentation.viewmodel.MainViewModel
import com.example.soptseminar.utils.Injection

class SignUpFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentSignUpBinding

    private lateinit var loginController: LoginController

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
        loginController = LoginController(Injection.provideLoginDataStore(requireContext()))

        binding.signUpOkBtn.setOnClickListener {
            val user = User(
                binding.signUpNameEdtxt.text.toString(),
                binding.signUpIdEdt.text.toString(),
                binding.signUpPassEdt.text.toString()
            )
            signUp(user)
            moveSignInPage(user.id, user.password)
        }

        return binding.root
    }

    private fun moveSignInPage(id: String, password: String) {
        findNavController().previousBackStackEntry?.savedStateHandle?.set("userId", id)
        findNavController().previousBackStackEntry?.savedStateHandle?.set("userPassword", password)
        findNavController().popBackStack()
    }

    private fun signUp(user: User) {
        if (loginController.isValidate(user))
            loginController.signUp(user)
        else
            Toast.makeText(requireContext(), "회원가입 실패", Toast.LENGTH_SHORT).show()
    }

}
