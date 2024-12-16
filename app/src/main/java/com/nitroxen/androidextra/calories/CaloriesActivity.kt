package com.nitroxen.androidextra.calories

import java.text.DecimalFormat
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import com.google.android.material.slider.RangeSlider
import com.google.android.material.slider.Slider
import com.nitroxen.androidextra.R

class CaloriesActivity : AppCompatActivity() {

    private lateinit var sGender:SwitchCompat
    private lateinit var rsHeight:RangeSlider
    private lateinit var tvHeight:TextView


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

    }

    private fun getListeners() {
        rsHeight.addOnChangeListener { _, value, _ ->
            var result = setFormatedInt(value)
            tvHeight.setText("$result cm")
        }
    }

    private fun getUI() {

    }

    private fun setFormatedInt(valor:Float):String{
        val df = DecimalFormat("#.##")
        return df.format(valor)
    }

    private fun setFormatedDouble(valor:String):Double{
        val df = DecimalFormat("#.##")
        return df.format(valor).toDouble()
    }

}