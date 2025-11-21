package com.jesse.gallery.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.jesse.gallery.R
import com.jesse.gallery.repository.Fotos

class FotosAdapter(
    private val lista: List<Fotos>
) : RecyclerView.Adapter<FotosAdapter.FotosViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FotosViewHolder {
        val layout = LayoutInflater.from(parent.context)

        val view = layout.inflate(
            R.layout.item_lista,
            parent,
            false
        )
        return FotosViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: FotosViewHolder,
        position: Int
    ) {
        val fotos = lista[position]
        holder.image.setImageResource(fotos.fotos)
        holder.bind(fotos)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    inner class FotosViewHolder(val view: View): RecyclerView.ViewHolder(view){
        val image: ImageView = view.findViewById(R.id.imageView)

        fun bind(foto : Fotos){
            image.setOnClickListener {
                MaterialAlertDialogBuilder(view.context)
                    .setTitle("oii")
                    .setMessage("Amoo um tantãoo vc ")
                    .setPositiveButton("ok") { dialog, which ->
                        dialog.dismiss()
                    }
                    .setNegativeButton("fechar"){ dialog, which ->
                        dialog.cancel()
                    }
                    .show()
            }
        }

    }

}