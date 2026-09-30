package com.example.trivialapp_base

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class Routes : NavKey {
    @Serializable
    data object TaskListScreen : Routes()

    @Serializable
    data object TaskDetailScreen : Routes()

    @Serializable
    data class Pantalla3(val taskId: String) : Routes()
}