package com.cwramirezg.tipocambio.data.model

import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity
data class TipoCambio(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val codigoMonedaOrigen: String,
    val nombreMonedaOrigen: String,
    val codigoMonedaDestino: String,
    val nombreMonedaDestino: String,
    val valorCompra: BigDecimal,
    val valorVenta: BigDecimal
) {
    @Ignore
    constructor() : this(
        0,
        "",
        "",
        "",
        "",
        BigDecimal.ZERO,
        BigDecimal.ZERO
    )

    class DiffCallback : DiffUtil.ItemCallback<TipoCambio>() {
        override fun areItemsTheSame(oldItem: TipoCambio, newItem: TipoCambio): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TipoCambio, newItem: TipoCambio): Boolean {
            return oldItem == newItem
        }

    }
}
