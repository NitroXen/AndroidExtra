package com.nitroxen.androidextra.todolist

sealed class TaskCategory {
    object Business : TaskCategory()
    object Personal : TaskCategory()
    object Other : TaskCategory()
}