package com.jesse.aulaframgent.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jesse.aulaframgent.databinding.FragmentConversasBinding

class ConversasFragment : Fragment() {
    private lateinit var binding: FragmentConversasBinding
    private var categoria:String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // recuperando os parametros passados pela activity
        categoria = arguments?.getString("categoria")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentConversasBinding.inflate(inflater, container, false)

        val nome = binding.editTextText.text
        binding.textViewCategory.text = categoria
        binding.buttonSubmit.setOnClickListener {
            binding.textViewResult.text = nome
        }

        return binding.root
    }
}