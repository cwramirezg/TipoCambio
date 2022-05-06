package com.cwramirezg.tipocambio.injection

import android.os.Bundle
import com.cwramirezg.tipocambio.data.source.dataSourceRepositoryModule
import com.cwramirezg.tipocambio.data.source.local.dataBaseModule
import com.cwramirezg.tipocambio.ui.seleccionarTipoCambio.SeleccionarTipoCambioViewModel
import com.cwramirezg.tipocambio.ui.tipoCambio.TipoCambioViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object Modules {
    fun get() = listOf(
        dataBaseModule,
        dataSourceRepositoryModule,
        module {
            viewModel { TipoCambioViewModel(get()) }
            viewModel { (bundle: Bundle?) ->
                SeleccionarTipoCambioViewModel(
                    bundle,
                    get()
                )
            }
        }
    )
}
