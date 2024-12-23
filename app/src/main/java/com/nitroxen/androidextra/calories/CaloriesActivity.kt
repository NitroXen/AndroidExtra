package com.nitroxen.androidextra.calories

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.SwitchCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import com.nitroxen.androidextra.R
import java.text.DecimalFormat
import kotlin.math.pow

class CaloriesActivity : AppCompatActivity() {


    companion object{
        val bmi = "VALUE_BMI"
        val cal = "VALUE_CALORIES"
    }

    private var weight: Int = 50
    private var age: Int = 25
    private var height: Int = 155
    private var gender: Boolean = false

    private lateinit var sGender: SwitchCompat
    private lateinit var rsHeight: RangeSlider
    private lateinit var tvHeight: TextView
    private lateinit var tvWeight: TextView
    private lateinit var tvAge: TextView
    private lateinit var fabSubstractWeight: FloatingActionButton
    private lateinit var fabSubstractAge: FloatingActionButton
    private lateinit var fabAddWeight: FloatingActionButton
    private lateinit var fabAddAge: FloatingActionButton
    private lateinit var btnCalculate: AppCompatButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_calories)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
        // inicializar elementos
        getInitialization()
        getListeners()
        getUI()
    }


    private fun getInitialization() {
        sGender = findViewById(R.id.sGender)
        rsHeight = findViewById(R.id.rsHeight)
        tvHeight = findViewById(R.id.tvHeight)
        tvWeight = findViewById(R.id.tvWeight)
        tvAge = findViewById(R.id.tvAge)
        fabSubstractWeight = findViewById(R.id.fabSubstractWeight)
        fabSubstractAge = findViewById(R.id.fabSubstractAge)
        fabAddWeight = findViewById(R.id.fabAddWeight)
        fabAddAge = findViewById(R.id.fabAddAge)
        btnCalculate = findViewById(R.id.btnCalculate)
    }

    private fun getListeners() {
        sGender.setOnClickListener { gender = !gender }
        rsHeight.addOnChangeListener { _, value, _ ->
            height = setFormatedInt(value).toInt()
            tvHeight.text = getString(R.string.height_total,height)
        }
        fabSubstractAge.setOnClickListener {
            modAge(false)
        }
        fabSubstractWeight.setOnClickListener {
            modWeight(false)
        }
        fabAddWeight.setOnClickListener {
            modWeight(true)
        }
        fabAddAge.setOnClickListener {
            modAge(true)
        }
        btnCalculate.setOnClickListener {
            val intent = Intent(this, ResultCaloriesActivity::class.java)
            intent.putExtra(bmi, setFormatedDouble(calculateBMI()))
            intent.putExtra(cal, calulateCalories())

            startActivity(intent)
        }
    }

    private fun getUI() {
        tvHeight.text = getString(R.string.height_base)
        tvWeight.text = String.format(weight.toString())
        tvAge.text = String.format(age.toString())
    }

    private fun modWeight(add: Boolean) {
        if (add) {
            ++weight
            tvWeight.text = "$weight"
            return
        }
        --weight
        tvWeight.text = "$weight"

    }

    private fun modAge(add: Boolean) {
        if (add) {
            ++age
            tvAge.text = "$age"
            return
        }
        --age
        tvAge.text = "$age"

    }


    // uso para formato y calculo
    private fun setFormatedInt(valor: Float): String {
        val df = DecimalFormat("#.##")
        return df.format(valor)
    }

    private fun setFormatedDouble(valor: Double): Double {
        val df = DecimalFormat("##.00")
        return df.format(valor).toDouble()
    }

    private fun calulateCalories(): Int {
        if (gender) {
            return (65 + (9.6 * weight) + (1.8 * height) - (4.7 * age)).toInt()
        }
        return (66 + (13.7 * weight) + (5 * height) - (6.8 * age)).toInt()
    }

    private fun calculateBMI(): Double {
        return weight / (height.toDouble() / 100).pow(2)
    }

}