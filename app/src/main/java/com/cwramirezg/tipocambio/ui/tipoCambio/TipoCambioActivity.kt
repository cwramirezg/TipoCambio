package com.cwramirezg.tipocambio.ui.tipoCambio

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.cwramirezg.tipocambio.R
import com.cwramirezg.tipocambio.databinding.ActivityTipoCambioBinding
import com.cwramirezg.tipocambio.ui.seleccionarTipoCambio.SeleccionarTipoCambioActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class TipoCambioActivity : AppCompatActivity(R.layout.activity_tipo_cambio) {

    private lateinit var binding: ActivityTipoCambioBinding
    private val viewModel: TipoCambioViewModel by viewModel()

    private val seleccionarRegister =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            when (result.resultCode) {
                RESULT_OK -> {
                    val newCodigo = result.data?.extras?.getString("codigo") ?: ""
                    viewModel.cambiar(newCodigo)
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTipoCambioBinding.inflate(layoutInflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        setContentView(binding.root)
        init()
    }

    private fun init() {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        setupAction()
    }

    private fun setupAction() {
        viewModel.action.observe(this) {
            when (it) {
                "cambiarOrigen" -> {
                    val intent = SeleccionarTipoCambioActivity.newInstance(
                        this,
                        viewModel.codigoDestino.value!!
                    )
                    seleccionarRegister.launch(intent)
                }
                "cambiarDestino" -> {
                    val intent = SeleccionarTipoCambioActivity.newInstance(
                        this,
                        viewModel.codigoOrigen.value!!
                    )
                    seleccionarRegister.launch(intent)
                }
            }
        }
    }

    companion object {
        fun newInstance(context: Context): Intent {
            return Intent(context, TipoCambioActivity::class.java)
        }
    }
}
