package com.cwramirezg.tipocambio.ui.seleccionarTipoCambio

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cwramirezg.tipocambio.data.model.TipoCambio
import com.cwramirezg.tipocambio.data.source.DataSourceRepositoryContract
import com.cwramirezg.tipocambio.util.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SeleccionarTipoCambioViewModel(
    bundle: Bundle?,
    private val repository: DataSourceRepositoryContract
) : ViewModel() {

    private val disposables = CompositeDisposable()

    val codigo = bundle?.getString(SeleccionarTipoCambioActivity.EXTRA_CODIGO) ?: ""

    private val _tipoList = MutableLiveData<List<TipoCambio>>()
    val tipoList: LiveData<List<TipoCambio>> = _tipoList

    private val _action = SingleLiveEvent<String>()
    val action: LiveData<String> = _action
    private val _version = SingleLiveEvent<String>()
    val version: LiveData<String> = _version

    init {
        disposables.add(
            repository.cambiarMoneda(codigo)
                .subscribeOn(Schedulers.io())
                .onErrorReturnItem(emptyList())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        _tipoList.value = it
                    },
                    {
                        it.printStackTrace()
                    }
                )
        )
    }

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }
}