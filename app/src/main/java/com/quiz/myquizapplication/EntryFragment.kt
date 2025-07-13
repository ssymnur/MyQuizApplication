package com.quiz.myquizapplication

import android.os.Bundle
import android.text.Layout.Directions
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.quiz.myquizapplication.databinding.FragmentEntryBinding


class EntryFragment : Fragment() {

    private var _binding: FragmentEntryBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEntryBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.entryButton.setOnClickListener{entry(it)}
        binding.registerButton.setOnClickListener{register(it)}

    }

    fun entry (view: View){
        val email= binding.emailText.text.toString()
        val password = binding.passwordText.text.toString()
        if (email.isNotEmpty() && password.isNotEmpty()){
            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(){task ->
                if (task.isSuccessful){
                    val action = EntryFragmentDirections.actionEntryFragmentToCategoriesFragment()
                    Navigation.findNavController(view).navigate(action)
                }
            }.addOnFailureListener { exception ->
                Toast.makeText(requireContext(),exception.localizedMessage,Toast.LENGTH_LONG).show()
            }
        }
    }

    fun register(view: View){
        val email= binding.emailText.text.toString()
        val password = binding.passwordText.text.toString()

        if (email.isNotEmpty() && password.isNotEmpty()){
            auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener() { task ->
                if (task.isSuccessful){
                    //kullanıcı oluşturuldu
                    val action = EntryFragmentDirections.actionEntryFragmentToCategoriesFragment()
                    Navigation.findNavController(view).navigate(action)
                }
            }.addOnFailureListener { exception ->
                Toast.makeText(requireContext(),exception.localizedMessage,Toast.LENGTH_LONG).show()
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}