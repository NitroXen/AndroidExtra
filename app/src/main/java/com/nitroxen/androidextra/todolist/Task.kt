package com.nitroxen.androidextra.todolist

data class Task(val text:String, val category: TaskCategory, var isSelected:Boolean = false)
