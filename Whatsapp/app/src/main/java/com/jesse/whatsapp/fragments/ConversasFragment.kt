package com.jesse.whatsapp.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import com.google.firebase.storage.FirebaseStorage
import com.jesse.whatsapp.R
import com.jesse.whatsapp.activitys.MensagensActivity
import com.jesse.whatsapp.adapters.ContatosAdapter
import com.jesse.whatsapp.adapters.ConversasAdapter
import com.jesse.whatsapp.databinding.FragmentConversasBinding
import com.jesse.whatsapp.model.Conversa
import com.jesse.whatsapp.model.Usuario
import com.jesse.whatsapp.util.Constantes
import com.jesse.whatsapp.util.exibirMensagens


class ConversasFragment : Fragment() {

    private lateinit var binding: FragmentConversasBinding
    private lateinit var eventoSnapshot: ListenerRegistration
    private lateinit var conversasAdapter: ConversasAdapter

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
        binding = FragmentConversasBinding.inflate(
            inflater, container, false
        )


        conversasAdapter = ConversasAdapter{ conversa ->
            val intent = Intent(context, MensagensActivity::class.java)

            val usuario = Usuario(
                id = conversa.idUsuarioDestinatario,
                nome = conversa.nome,
                foto = conversa.foto
            )
                intent.putExtra("dadosDestinatario" , usuario)
                intent.putExtra("origem" , Constantes.ORIGEM_CONVERSA)
            startActivity(intent)
        }
        binding.recyclerViewConversas.adapter = conversasAdapter
        binding.recyclerViewConversas.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewConversas.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayoutManager.VERTICAL
            )
        )

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        adicionarListenerConversas()
    }

    override fun onDestroy() {
        super.onDestroy()
        eventoSnapshot.remove()
    }

    private fun adicionarListenerConversas() {

        val idUsuarioRemetente = firebaseAuth.currentUser?.uid
        if( idUsuarioRemetente != null ){
            eventoSnapshot = firestore
                .collection(Constantes.CONVERSAS)
                .document( idUsuarioRemetente )
                .collection(Constantes.ULTIMAS_CONVERSAS)
                .orderBy("data" , Query.Direction.DESCENDING)
                .addSnapshotListener { querySnapshot, erro ->

                    if( erro != null ){
                        activity?.exibirMensagens("Erro ao recuperar conversas")
                    }

                    val listaConversas = mutableListOf<Conversa>()
                    val documentos = querySnapshot?.documents

                    documentos?.forEach { documentSnapshot ->

                        val conversa = documentSnapshot.toObject(Conversa::class.java)
                        if( conversa != null ){
                            listaConversas.add( conversa )
                            Log.i("exibicao_conversas", "${conversa.nome} - ${conversa.ultimaMensagem}")
                        }
                    }

                    if( listaConversas.isNotEmpty() ){
                        conversasAdapter.adicionarLista( listaConversas )
                    }

                }
        }

    }

}