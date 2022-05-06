package com.cwramirezg.tipocambio.data.source

import com.cwramirezg.tipocambio.data.model.TipoCambio
import com.cwramirezg.tipocambio.data.source.local.DataSourceLocalContract
import io.reactivex.Single

class DataSourceRepository(
    private val local: DataSourceLocalContract
) : DataSourceRepositoryContract {

    override fun getTipoCambioByCodigo(
        codigoMoneda1: String,
        codigoMoneda2: String
    ): Single<TipoCambio> {
        return local.getTipoCambioByCodigo(codigoMoneda1, codigoMoneda2)
    }

    override fun cambiarMoneda(codigoMoneda: String): Single<List<TipoCambio>> {
        return local.cambiarMoneda(codigoMoneda)
    }
}