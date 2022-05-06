package com.cwramirezg.tipocambio.data.source.local

import com.cwramirezg.tipocambio.data.model.TipoCambio
import io.reactivex.Single

class DataSourceLocal(
    private val database: Database
) : DataSourceLocalContract {

    override fun getTipoCambioByCodigo(
        codigoMoneda1: String,
        codigoMoneda2: String
    ): Single<TipoCambio> {
        return database.tipoCambioDao().getByCodigo(codigoMoneda1, codigoMoneda2)
    }

    override fun cambiarMoneda(codigoMoneda: String): Single<List<TipoCambio>> {
        return database.tipoCambioDao().cambiarMoneda(codigoMoneda)
    }
}