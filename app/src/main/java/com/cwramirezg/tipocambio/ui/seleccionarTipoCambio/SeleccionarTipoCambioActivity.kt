package com.cwramirezg.tipocambio.ui.seleccionarTipoCambio

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.cwramirezg.tipocambio.R
import com.cwramirezg.tipocambio.databinding.ActivitySeleccionarTipoCambioBinding
import com.cwramirezg.tipocambio.ui.seleccionarTipoCambio.adapter.ItemAdapter
import com.cwramirezg.tipocambio.util.SimpleDividerItemDecoration
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class SeleccionarTipoCambioActivity : AppCompatActivity(R.layout.activity_seleccionar_tipo_cambio) {

    private lateinit var binding: ActivitySeleccionarTipoCambioBinding
    private val viewModel: SeleccionarTipoCambioViewModel by viewModel { parametersOf(intent.extras) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeleccionarTipoCambioBinding.inflate(layoutInflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        setContentView(binding.root)
        init()
    }

    private fun init() {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        setupAction()
        setupRv()
    }

    private fun setupAction() {
        viewModel.action.observe(this) {
            when (it) {
                "getDescarga" -> {

                }
            }
        }
    }

    private fun setupRv() {
        binding.rvItem.apply {
            setHasFixedSize(true)
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(SimpleDividerItemDecoration(context, R.drawable.line_divider_white))

        }
        binding.rvItem.adapter = ItemAdapter(viewModel.codigo) {
            val intent = Intent()
            val tipo = viewModel.tipoList.value!![it]
            if (viewModel.codigo == tipo.codigoMonedaOrigen) {
                intent.putExtra("codigo", tipo.codigoMonedaDestino)
            } else {
                intent.putExtra("codigo", tipo.codigoMonedaOrigen)
            }
            setResult(RESULT_OK, intent)
            finish()
        }
        viewModel.tipoList.observe(this) {
            if (it.isNotEmpty()) {
                binding.rvItem.visibility = View.VISIBLE
                binding.tvSinData.visibility = View.GONE
                (binding.rvItem.adapter as ItemAdapter).submitList(it)
            } else {
                binding.rvItem.visibility = View.GONE
                binding.tvSinData.visibility = View.VISIBLE
            }
        }
    }

    companion object {
        const val EXTRA_CODIGO = "CODIGO"
        fun newInstance(
            context: Context,
            codigo: String
        ): Intent {
            val intent = Intent(context, SeleccionarTipoCambioActivity::class.java)
            val bundle = Bundle()
            bundle.putString(EXTRA_CODIGO, codigo)
            intent.putExtras(bundle)
            return intent
        }
    }
}
