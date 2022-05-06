package com.cwramirezg.tipocambio.data.source

import com.cwramirezg.tipocambio.data.source.local.DataSourceLocal
import com.cwramirezg.tipocambio.data.source.local.DataSourceLocalContract
import org.koin.dsl.module

val dataSourceRepositoryModule = module {
    factory<DataSourceLocalContract> { DataSourceLocal(get()) }
    single<DataSourceRepositoryContract> {
        DataSourceRepository(get())
    }
}
