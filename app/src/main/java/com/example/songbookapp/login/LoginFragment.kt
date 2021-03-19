package com.example.songbookapp.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.songbookapp.MainActivity
import com.example.songbookapp.R
import com.example.songbookapp.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {

    private var binding: FragmentLoginBinding? = null
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val LOGIN_DEBUG = "LOGIN_DEBUG"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentLoginBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            loginFragment = this@LoginFragment
        }
    }

    fun goToRegistration() {
        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
    }

    fun loginUser() {
        val email = binding?.loginEmailInputEditText?.text.toString()
        val password = binding?.loginPasswordInputEditText?.text.toString()

        if(email.isNotEmpty() && password.isNotEmpty()){
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    if(it.user != null) {
                        val intent = Intent(requireContext(), MainActivity::class.java).apply {
                            flags = (Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        }
                        startActivity(intent)
                        activity?.finish()
                    }
                }
                .addOnFailureListener{
                    Log.d(LOGIN_DEBUG, it.message.toString())
                }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
    
}