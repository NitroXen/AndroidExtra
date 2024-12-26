package com.nitroxen.androidextra.todolist

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.nitroxen.androidextra.R

class TodoActivity : AppCompatActivity() {

//    private val categories = listOf(
//        TaskCategory.Business,
//        TaskCategory.Personal,
//        TaskCategory.Other
//    )

    private val tasks = mutableListOf(
        Task("TestBusiness", TaskCategory.Business),
        Task("TestPersonal", TaskCategory.Personal),
        Task("TestOther", TaskCategory.Other)
    )



//    private lateinit var rvCategories: RecyclerView
//    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var rvTasks: RecyclerView
    private lateinit var tasksAdapter: TasksAdapter
    private lateinit var fabAddTask: FloatingActionButton
    private lateinit var pbTask: ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_todo)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initalization()
        getListeners()
        initUI()

    }

    private fun initalization() {
        pbTask = findViewById(R.id.pbTask)
//        rvCategories = findViewById(R.id.rvCategories)
        rvTasks = findViewById(R.id.rvTasks)
        fabAddTask = findViewById(R.id.fabAddTask)
    }

    private fun getListeners() {
        fabAddTask.setOnClickListener {
            showDialog()
        }
    }


    private fun initUI() {
//        categoryAdapter = CategoryAdapter(categories){updateCategories(it)}
//        rvCategories.layoutManager =
//            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//        rvCategories.adapter = categoryAdapter

        tasksAdapter = TasksAdapter(tasks, {onItemSelection(it)})
        rvTasks.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvTasks.adapter = tasksAdapter

    }

    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_tasks)

        val btnAddTask: Button = dialog.findViewById(R.id.btnAddTask)
        val etTask: EditText = dialog.findViewById(R.id.etTask)
        val rgCategories: RadioGroup = dialog.findViewById(R.id.rgCategories)

        btnAddTask.setOnClickListener {
            val currentTask = etTask.text.toString()
            if (currentTask.isNotEmpty()) {
                val selectedId = rgCategories.checkedRadioButtonId
                val selectedRadioButton: RadioButton = rgCategories.findViewById(selectedId)
                val currentCategory: TaskCategory = when (selectedRadioButton.text) {
                    getString(R.string.personal) -> TaskCategory.Personal
                    getString(R.string.business) -> TaskCategory.Business
                    else -> TaskCategory.Other
                }

                tasks.add(Task(currentTask, currentCategory))
                updateTask()
                dialog.hide()
            }
        }

        dialog.show()
    }


    private fun onItemSelection(position:Int){
        tasks[position].isSelected  =!tasks[position].isSelected
        updateProgress()
        updateTask()
    }

//    private fun updateCategories(pos:Int){
//        categories[pos].isSelected = !categories[pos].isSelected
//        categoryAdapter.notifyItemChanged(pos)
//        updateTask()
//    }

    private fun updateProgress(){
        var num = 0.0
        tasks.map { if(it.isSelected) ++num }

        Log.i("num",((3.0/5.0)*100).toString())
        pbTask.progress = ((num/tasks.size)*pbTask.max).toInt()
        updateTask()
    }


    private fun updateTask() {
//        val selectedCategories: List<TaskCategory> = categories.filter { it.isSelected }
//        val newTasks = tasks.filter { selectedCategories.contains(it.category) }
//        tasksAdapter.tasks = newTasks
        tasksAdapter.notifyDataSetChanged()
    }
}