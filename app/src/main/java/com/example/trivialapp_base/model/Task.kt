package com.example.trivialapp_base.model

import kotlinx.serialization.Serializable

@Serializable
data class Task(
    val id: String,
    val title: String,
    val description: String,
    val isCompleted: Boolean
)