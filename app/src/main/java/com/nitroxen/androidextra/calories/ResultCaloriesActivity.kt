package com.nitroxen.androidextra.calories

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.nitroxen.androidextra.R

class ResultCaloriesActivity : AppCompatActivity() {

    private lateinit var tvBMI: TextView
    private lateinit var tvCalories: TextView
    private lateinit var btnReCalculate:AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result_calories)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val resultcalories = intent.extras?.getInt(CaloriesActivity.cal) ?: 0
        val resultBMI = intent.extras?.getDouble(CaloriesActivity.bmi) ?: 0.0
        inicialization()
        getListeners()
        getUI(resultBMI,resultcalories)

    }

    private fun inicialization(){
        tvBMI  =findViewById(R.id.tvBMI)
        tvCalories = findViewById(R.id.tvCalories)
        btnReCalculate = findViewById(R.id.btnReCalculate)

    }

    private fun getListeners(){
        btnReCalculate.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun getUI(bmi:Double, calories:Int){
        getBMI(bmi)
        tvCalories.text ="$calories"
    }


    private fun getBMI(bmi:Double)=when(bmi){
        in 0.00 .. 18.49 -> {
            tvBMI.text = getString(R.string.low_bmi)
            tvBMI.setTextColor(getColor(R.color.peso_bajo))
        }
        in 18.50 .. 24.99 -> {
            tvBMI.text = getString(R.string.normal_bmi)
            tvBMI.setTextColor(getColor(R.color.peso_normal))
        }
        in 25.00 .. 29.99 -> {
            tvBMI.text = getString(R.string.over_bmi)
            tvBMI.setTextColor(getColor(R.color.peso_sobrepeso))
        }
        in 30.00 .. 100.00 -> {
            tvBMI.text = getString(R.string.obesity_bmi)
            tvBMI.setTextColor(getColor(R.color.obesidad))
        }
        else -> tvBMI.text=getString(R.string.error)
    }


}