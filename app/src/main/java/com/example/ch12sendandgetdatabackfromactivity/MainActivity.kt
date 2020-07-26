package com.example.ch12sendandgetdatabackfromactivity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    val SECOND_ACTIVITY = 1000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        input_weight.setHint("Peso (Kg)")
        input_height.setHint("Altura (m)")

        btn_send_data.setOnClickListener {
            val m_intent = Intent(this@MainActivity, SecondActivity::class.java)
            val m_bundle = Bundle()

            m_bundle.putFloat("weight", input_weight.text.toString().toFloat())
            m_bundle.putFloat("height", input_height.text.toString().toFloat())
            m_intent.putExtra("main_activity_data", m_bundle)

            startActivityForResult(m_intent, SECOND_ACTIVITY)
        }
    }

    override fun onResume() {
        super.onResume()
        clearInputs()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if ((requestCode == SECOND_ACTIVITY) and (resultCode == Activity.RESULT_OK)) {
            val bmi = data!!.getFloatExtra("second_activity_data", 1.0F)
            val bmiString ="%.2f".format(bmi)
            input_height.setText("")
            input_weight.setText("")
            txt_bmi.setText("IMC : $bmiString ${getBMIDescription(bmi)}")
        }
    }

    private fun getBMIDescription(bmi: Float): String {
        return when (bmi) {
            in 0.0..17.0 -> "Muito abaixo do peso"
            in 17.0..18.5 -> "Abaixo do peso"
            in 18.6..24.9 -> "Peso Normal"
            in 25.0..29.9 -> "Acima do peso"
            in 30.0..34.99 -> "Obesidade 1"
            in 35.00..39.99 -> "Obesidade 2 (severa)"
            else -> "Obesidade 3 (mórbida)"
        }
    }

    private fun clearInputs() { //
        input_weight.setText("")
        input_height.setText("")
    }
}
