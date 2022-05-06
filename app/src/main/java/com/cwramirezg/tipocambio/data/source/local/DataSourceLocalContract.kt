package com.cwramirezg.tipocambio.data.source.local

import com.cwramirezg.tipocambio.data.model.TipoCambio
import io.reactivex.Single

interface DataSourceLocalContract {
    fun getTipoCambioByCodigo(codigoMoneda1: String, codigoMoneda2: String): Single<TipoCambio>
    fun cambiarMoneda(codigoMoneda: String): Single<List<TipoCambio>>
}