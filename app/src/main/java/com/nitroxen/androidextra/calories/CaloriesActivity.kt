package com.nitroxen.androidextra.calories

import java.text.DecimalFormat
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import com.google.android.material.slider.Slider
import com.nitroxen.androidextra.R

class CaloriesActivity : AppCompatActivity() {

    private var weight: Int = 50
    private var age: Int = 25
    private var height : Int = 155

    private lateinit var sGender: SwitchCompat
    private lateinit var rsHeight: RangeSlider
    private lateinit var tvHeight: TextView
    private lateinit var tvWeight: TextView
    private lateinit var tvAge: TextView
    private lateinit var fabSubstractWeight: FloatingActionButton
    private lateinit var fabSubstractAge: FloatingActionButton
    private lateinit var fabAddWeight: FloatingActionButton
    private lateinit var fabAddAge: FloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_calories)
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

    }

    private fun getListeners() {
        rsHeight.addOnChangeListener { _, value, _ ->
            height = setFormatedInt(value).toInt()
            tvHeight.setText("$height cm")
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
    }

    private fun getUI() {
        tvHeight.text = "150 cm"
        tvWeight.text = weight.toString()
        tvAge.text = age.toString()
        tvHeight.text = height.toString()

    }

    private fun modWeight(add: Boolean) {
        if (add) {
            ++weight
            tvWeight.text = weight.toString()
            return
        }
        --weight
        tvWeight.text = weight.toString()

    }

    private fun modAge(add: Boolean) {
        if (add) {
            ++age
            tvAge.text = age.toString()
            return
        }
        --age
        tvAge.text = age.toString()

    }

    private fun setFormatedInt(valor: Float): String {
        val df = DecimalFormat("#.##")
        return df.format(valor)
    }

    private fun setFormatedDouble(valor: String): Double {
        val df = DecimalFormat("#.##")
        return df.format(valor).toDouble()
    }

    private fun calulateCalories():Double{
        if(sGender.isActivated){
           return (65+(9.6*weight)+(1.8*height)-(4.7*age))
        }
        return 66+(13.7*weight)+(5*height)-(6.8*age)
    }

    private fun calculateBMI():Int{
        return weight/((height/100)*(height/100))
    }

}