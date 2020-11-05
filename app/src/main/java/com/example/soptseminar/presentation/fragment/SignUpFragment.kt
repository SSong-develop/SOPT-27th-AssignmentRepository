package com.example.soptseminar.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.soptseminar.R
import com.example.soptseminar.databinding.FragmentSignUpBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SignUpFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignUpFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentSignUpBinding>(inflater,R.layout.fragment_sign_up,container,false)
        binding.signUpOkBtn.setOnClickListener {view : View ->
            // use safeArgs and sharedPref
            view.findNavController().navigate(R.id.action_signUpFragment_to_mainFragment)
        }
        return binding.root
    }
}