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
import com.example.soptseminar.databinding.FragmentSignUpBinding
import com.example.soptseminar.model.User
import com.example.soptseminar.presentation.viewmodel.MainViewModel

class SignUpFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentSignUpBinding>(
            inflater,
            R.layout.fragment_sign_up,
            container,
            false
        )
        setObserver()

        binding.signUpOkBtn.setOnClickListener {
            if (isValidate(binding)) {
                viewModel.setUser(
                    User(
                        binding.signUpNameEdtxt.text.toString(),
                        binding.signUpEmailEdt.text.toString(),
                        binding.signUpPassEdt.text.toString()
                    )
                )
                showToast("회원가입 성공")
                findNavController().popBackStack()
            } else
                showToast("회원가입 실패, 빈칸 없이 작성해주세요")

        }
        return binding.root
    }

    private fun isValidate(binding: FragmentSignUpBinding): Boolean {
        return binding.signUpEmailEdt.text.isNotBlank() && binding.signUpNameEdtxt.text.isNotBlank() && binding.signUpPassEdt.text.isNotBlank()
    }

    private fun setObserver() {
        viewModel.user.observe(viewLifecycleOwner) {
            viewModel.signUp()
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

}
