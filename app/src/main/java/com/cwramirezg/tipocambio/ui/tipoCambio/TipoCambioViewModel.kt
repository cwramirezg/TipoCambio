package com.cwramirezg.tipocambio.ui.tipoCambio

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cwramirezg.tipocambio.data.model.TipoCambio
import com.cwramirezg.tipocambio.data.source.DataSourceRepositoryContract
import com.cwramirezg.tipocambio.util.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.math.BigDecimal
import java.math.RoundingMode

class TipoCambioViewModel(
    private val repository: DataSourceRepositoryContract
) : ViewModel() {

    private val disposables = CompositeDisposable()
    private val _action = SingleLiveEvent<String>()
    val action: LiveData<String> = _action
    private val _tipoCambio = MutableLiveData<TipoCambio>()
    val tipoCambio: LiveData<TipoCambio> = _tipoCambio
    private val _codigoOrigen = MutableLiveData("USD")
    val codigoOrigen: LiveData<String> = _codigoOrigen
    private val _nombreOrigen = MutableLiveData("Dólar")
    val nombreOrigen: LiveData<String> = _nombreOrigen
    private val _codigoDestino = MutableLiveData("PEN")
    val codigoDestino: LiveData<String> = _codigoDestino
    private val _nombreDestino = MutableLiveData("Soles")
    val nombreDestino: LiveData<String> = _nombreDestino
    private val _text = MutableLiveData("Compra: 0.00 | Venta: 0.00")
    val text: LiveData<String> = _text
    val valor = MutableLiveData("1")
    private val _resultado = MutableLiveData("0")
    val resultado: LiveData<String> = _resultado

    var cambio = ""

    init {
        obtenerTipoCambios()
    }

    private fun obtenerTipoCambios() {
        Timber.d("1->${_codigoOrigen.value}|${_codigoDestino.value}")
        disposables.add(
            repository.getTipoCambioByCodigo(_codigoOrigen.value!!, _codigoDestino.value!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        Timber.d("$it")
                        _tipoCambio.value = it
                        if (it.codigoMonedaOrigen == _codigoOrigen.value) {
                            _nombreOrigen.value = _tipoCambio.value!!.nombreMonedaOrigen
                            _nombreDestino.value = _tipoCambio.value!!.nombreMonedaDestino
                        } else {
                            _nombreOrigen.value = _tipoCambio.value!!.nombreMonedaDestino
                            _nombreDestino.value = _tipoCambio.value!!.nombreMonedaOrigen
                        }
                        _text.value =
                            "Compra: ${_tipoCambio.value!!.valorCompra} | Venta: ${_tipoCambio.value!!.valorVenta}"
                    },
                    {
                        it.printStackTrace()
                    }
                )
        )
    }

    fun cambioDeCodigo() {
        val temp = _codigoOrigen.value
        _codigoOrigen.value = _codigoDestino.value
        _codigoDestino.value = temp
        obtenerTipoCambios()
    }

    fun calcularOperacion() {
        if (_codigoOrigen.value == _tipoCambio.value!!.codigoMonedaOrigen) {
            _resultado.value =
                BigDecimal(valor.value!!).divide(
                    _tipoCambio.value!!.valorVenta,
                    2,
                    RoundingMode.HALF_UP
                ).toString()
        } else {
            _resultado.value =
                BigDecimal(valor.value!!).multiply(_tipoCambio.value!!.valorCompra).toString()
        }
    }

    fun cambiarOrigen() {
        cambio = "cambiarOrigen"
        _action.value = "cambiarOrigen"
    }

    fun cambiarDestino() {
        cambio = "cambiarDestino"
        _action.value = "cambiarDestino"
    }

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }

    fun cambiar(newCodigo: String) {
        when (cambio) {
            "cambiarOrigen" -> {
                _codigoOrigen.value = newCodigo
                obtenerTipoCambios()
            }
            "cambiarDestino" -> {
                _codigoDestino.value = newCodigo
                obtenerTipoCambios()
            }
        }
    }
}