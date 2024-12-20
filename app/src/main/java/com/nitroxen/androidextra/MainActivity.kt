package com.nitroxen.androidextra

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.nitroxen.androidextra.calories.CaloriesActivity
import com.nitroxen.androidextra.todolist.TodoActivity

class MainActivity : AppCompatActivity() {

    private lateinit var btnCalories: AppCompatButton
    private lateinit var btnTodoList: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        getInitialization()
        getListeners()
    }

    private fun getInitialization() {
        btnCalories = findViewById(R.id.btnCalories)
        btnTodoList = findViewById(R.id.btnTodoList)
    }

    private fun getListeners() {
        btnCalories.setOnClickListener {
            val intent = Intent(this, CaloriesActivity::class.java)
            startActivity(intent)
        }

        btnTodoList.setOnClickListener {
            val intent = Intent(this, TodoActivity::class.java)
            startActivity(intent)
        }
    }


}