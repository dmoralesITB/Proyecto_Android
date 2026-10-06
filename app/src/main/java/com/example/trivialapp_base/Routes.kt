package com.example.trivialapp_base

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed class Routes : NavKey {
    @Serializable
    data object Pantalla1 : Routes()

    @Serializable
    data object Pantalla2 : Routes()

    @Serializable
    data class Pantalla3(val taskId: String) : Routes()
    @Serializable
    data class Pantalla4(val taskId: String) : Routes()

}