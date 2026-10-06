package com.example.trivialapp_base.viewmodel

import android.app.Application
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import com.example.trivialapp_base.model.TaskJsonReader
import com.example.trivialapp_base.model.Task

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    var taskList = mutableStateListOf<Task>()
        private set

    var selectedTask by mutableStateOf<Task?>(null)
        private set

    init {
        loadInitialTasks()
    }

    private fun loadInitialTasks() {
        taskList.clear()
        try {
            val tasks = TaskJsonReader.readTasks(getApplication())
            Log.d("TaskVM", "Tareas leídas: ${tasks.size}")
            taskList.addAll(tasks)
        } catch (e: Exception) {
            Log.e("TaskVM", "Error cargando task.json", e)
        }
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

    fun toggleTaskCompletion(task: Task) {
        val index = taskList.indexOf(task)
        if (index != -1) {
            taskList[index] = task.copy(isCompleted = !task.isCompleted)
        }
    }

    fun deleteTask(id: String) {
        taskList.removeAll { it.id == id }
        if (selectedTask?.id == id) {
            selectedTask = null
        }
    }
}