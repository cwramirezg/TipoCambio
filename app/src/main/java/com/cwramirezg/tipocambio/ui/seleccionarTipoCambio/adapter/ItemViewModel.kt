package com.cwramirezg.tipocambio.ui.seleccionarTipoCambio.adapter

import com.cwramirezg.tipocambio.data.model.TipoCambio
import java.math.BigDecimal
import java.math.RoundingMode

class ItemViewModel(
    private val tipoCambio: TipoCambio,
    private val codigo: String
) {
    private var moneda = ""

    val nombre
        get() = if (codigo == tipoCambio.codigoMonedaOrigen) {
            tipoCambio.nombreMonedaDestino
        } else {
            tipoCambio.nombreMonedaOrigen
        }

    val cambio
        get() = "1 $codigo = ${calculo()} $moneda"

    fun calculo(): String {
        if (codigo == tipoCambio.codigoMonedaOrigen) {
            moneda = tipoCambio.codigoMonedaDestino
            return BigDecimal.ONE.multiply(tipoCambio.valorCompra).toString()
        } else {
            moneda = tipoCambio.codigoMonedaOrigen
            return BigDecimal.ONE.divide(
                tipoCambio.valorVenta,
                2,
                RoundingMode.HALF_UP
            ).toString()
        }
    }
}