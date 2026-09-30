package com.example.trivialapp_base.model

data class Task(
    val id: String,
    var title: String,
    var description: String,
    var isCompleted: Boolean = false
)