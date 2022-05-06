package com.cwramirezg.tipocambio.ui.seleccionarTipoCambio.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cwramirezg.tipocambio.data.model.TipoCambio
import com.cwramirezg.tipocambio.databinding.ItemTipoBinding

class ItemAdapter(
    private val codigo: String,
    val onclick: (Int) -> Unit
) : ListAdapter<TipoCambio, ItemAdapter.ViewHolder>(TipoCambio.DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemTipoBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onclick
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), codigo)
    }

    inner class ViewHolder(
        private val binding: ItemTipoBinding,
        val onclick: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                onclick(bindingAdapterPosition)
            }
        }

        fun bind(obj: TipoCambio, codigo: String) {
            with(binding) {
                viewModel = ItemViewModel(obj, codigo)
                executePendingBindings()
            }
        }
    }
}