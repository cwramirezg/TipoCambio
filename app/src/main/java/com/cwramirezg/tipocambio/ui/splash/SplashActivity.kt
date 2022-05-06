package com.cwramirezg.tipocambio.ui.splash

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.cwramirezg.tipocambio.ui.tipoCambio.TipoCambioActivity

class SplashActivity : AppCompatActivity() {

    var handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handler.postDelayed(
            {
                startActivity(TipoCambioActivity.newInstance(this))
                finish()
            },
            1500
        )
    }

    override fun onDestroy() {
        handler.removeCallbacksAndMessages(null)
        super.onDestroy()
    }

}
