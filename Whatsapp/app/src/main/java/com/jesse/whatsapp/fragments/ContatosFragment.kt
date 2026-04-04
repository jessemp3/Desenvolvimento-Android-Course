package com.jesse.whatsapp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.jesse.whatsapp.activitys.MensagensActivity
import com.jesse.whatsapp.adapters.ContatosAdapter
import com.jesse.whatsapp.databinding.FragmentContatosBinding
import com.jesse.whatsapp.model.Usuario
import com.jesse.whatsapp.util.Constantes


class ContatosFragment : Fragment() {
    private lateinit var binding: FragmentContatosBinding
    private lateinit var eventoSnapshot: ListenerRegistration
    private lateinit var adapter: ContatosAdapter


    private val firebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    private val firestore by lazy {
        FirebaseFirestore.getInstance()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContatosBinding.inflate(inflater, container, false)

        adapter = ContatosAdapter{ usuario ->
             val intent = Intent(requireContext(), MensagensActivity::class.java)
                 .putExtra("dadosDestinatario" , usuario)
                 .putExtra("origem" , Constantes.ORIGEM_CONTATO)
            startActivity(intent)
        }
        binding.rvContatos.adapter = adapter
        binding.rvContatos.layoutManager = LinearLayoutManager(requireContext())
        binding.rvContatos.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayoutManager.VERTICAL
            )
        )

        return binding.root

    }

    override fun onStart() {
        super.onStart()
        adicionarListernerContatos()
    }

    override fun onDestroy() {
        super.onDestroy()
        eventoSnapshot.remove() // para remove ro listener fora da tela
    }

    private fun adicionarListernerContatos() {
        eventoSnapshot = firestore
            .collection("usuarios")
            .addSnapshotListener { query, exception ->
                val listaContatos = mutableListOf<Usuario>()
                val document = query?.documents
                document?.forEach { documentSnapshot ->
                    val usuario = documentSnapshot.toObject(Usuario::class.java)
                    val idUserLogado = firebaseAuth.currentUser?.uid

                    if (usuario != null && idUserLogado != null) {

                        if (usuario.id != idUserLogado) {
                            listaContatos.add(usuario)
                        }
                    }
                }

                // recylerView
                if (listaContatos.isNotEmpty()) {
                    adapter.adicionarLista(listaContatos)
                }
            }
    }
}
