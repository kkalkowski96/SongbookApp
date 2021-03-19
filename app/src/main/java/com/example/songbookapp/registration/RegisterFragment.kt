package com.example.songbookapp.registration

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.songbookapp.R
import com.example.songbookapp.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class RegisterFragment : Fragment() {

    private var binding: FragmentRegisterBinding? = null
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val REGISTER_TAH = "REGISTER_TAG"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentRegisterBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            registerFragment = this@RegisterFragment
        }
    }

    fun registerUser() {
        val email = binding?.registerEmailInputEditText?.text.toString()
        val password = binding?.registerPasswordInputEditText?.text.toString()


        if (email.isNotEmpty() && password.isNotEmpty()){
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                }
                .addOnFailureListener{
                    Log.d(REGISTER_TAH, it.message.toString())
                }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}