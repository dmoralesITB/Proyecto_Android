package com.example.trivialapp_base.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.trivialapp_base.model.Task

class TaskViewModel : ViewModel() {

    // Lista de tareas (StateList para que Compose reaccione a los cambios)
    var taskList = mutableStateListOf<Task>()
        private set

    // Tarea seleccionada actualmente para ver/editar en el detalle (HU02 / HU03)
    var selectedTask by mutableStateOf<Task?>(null)
        private set

    init {
        loadInitialTasks()
    }


    private fun loadInitialTasks() {
        taskList.clear()
        taskList.addAll(
            listOf(
                Task("1", "Configurar entorno de desarrollo", "Instalar Android Studio y configurar repositorio GitHub", true),
                Task("2", "Diseñar UI Sprint 1", "Crear las pantallas principales en Jetpack Compose", false),
                Task("3", "Crear archivo de datos iniciales", "Definir las tareas en formato JSON o XML", false)
            )
        )
    }


    fun selectTaskById(taskId: String) {
        selectedTask = taskList.find { it.id == taskId }
    }


    fun updateTask(id: String, newTitle: String, newDescription: String) {
        val index = taskList.indexOfFirst { it.id == id }
        if (index != -1) {
            val updatedTask = taskList[index].copy(
                title = newTitle,
                description = newDescription
            )
            taskList[index] = updatedTask
            selectedTask = updatedTask
        }
    }

    // Cambiar estado completado/pendiente
    fun toggleTaskCompletion(task: Task) {
        val index = taskList.indexOf(task)
        if (index != -1) {
            taskList[index] = task.copy(isCompleted = !task.isCompleted)
        }
    }
}