package com.nitroxen.androidextra.todolist

sealed class TaskCategory(var isSelected:Boolean = true) {
    object Business : TaskCategory()
    object Personal : TaskCategory()
    object Other : TaskCategory()
}