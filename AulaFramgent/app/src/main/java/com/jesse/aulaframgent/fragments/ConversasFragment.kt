package com.jesse.aulaframgent.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class ConversasFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate( // inflando o layout do fragment
            com.jesse.aulaframgent.R.layout.fragment_conversas,
            container,
            false
        )
    }
}