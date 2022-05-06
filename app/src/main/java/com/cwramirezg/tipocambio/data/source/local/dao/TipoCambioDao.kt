package com.cwramirezg.tipocambio.data.source.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.cwramirezg.tipocambio.data.model.TipoCambio
import io.reactivex.Single

@Dao
interface TipoCambioDao : BaseDao<TipoCambio> {

    @Query("SELECT * FROM TipoCambio")
    fun getAll(): Single<List<TipoCambio>>

    @Query("DELETE FROM TipoCambio")
    fun deleteAll()

    @Query(
        """
        SELECT *
        FROM TipoCambio tc
        WHERE (codigoMonedaOrigen = :codigoMonedaOrigen AND codigoMonedaDestino = :codigoMonedaDestino) 
        OR (codigoMonedaOrigen = :codigoMonedaDestino AND codigoMonedaDestino = :codigoMonedaOrigen)
        """
    )
    fun getByCodigo(codigoMonedaOrigen: String, codigoMonedaDestino: String): Single<TipoCambio>

    @Query(
        """
        SELECT *
        FROM TipoCambio tc
        WHERE tc.codigoMonedaDestino = :codigoMoneda OR tc.codigoMonedaOrigen = :codigoMoneda
        """
    )
    fun cambiarMoneda(
        codigoMoneda: String
    ): Single<List<TipoCambio>>

}
