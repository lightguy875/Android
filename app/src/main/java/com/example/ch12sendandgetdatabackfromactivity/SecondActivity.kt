package com.example.ch12sendandgetdatabackfromactivity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val bundle = intent.getBundleExtra("main_activity_data")
        val height = bundle?.getFloat("height")
        val weight = bundle?.getFloat("weight")
        txt_intentdata.text = "Altura(m) : $height | Peso(kg): $weight"

        btn_calculate.setOnClickListener {
            val m_intent = Intent()
            val m_bmi = (weight!! / (height!! * height!!))
            m_intent.putExtra("second_activity_data", m_bmi)
            setResult(Activity.RESULT_OK,m_intent)
            finish()
        }


    }
}