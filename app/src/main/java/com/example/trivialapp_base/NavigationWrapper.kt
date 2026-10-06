package com.example.trivialapp_base

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.trivialapp_base.view.TaskDetailScreen
import com.example.trivialapp_base.view.TaskListScreen
import com.example.trivialapp_base.view.TaskViewScreen
import com.example.trivialapp_base.viewmodel.TaskViewModel

@Composable
fun NavigationWrapper(viewModel: TaskViewModel) {
    val backStack = rememberNavBackStack(Routes.Pantalla1)

    NavDisplay(
        backStack = backStack,
        onBack = {
            if (backStack.size > 1) {
                backStack.removeAt(backStack.lastIndex)
            }
        },
        entryProvider = entryProvider {
            // 1. Pantalla principal (Lista)
            entry<Routes.Pantalla1> {
                TaskListScreen(
                    viewModel = viewModel,
                    onTaskClick = { taskId ->
                        backStack.add(Routes.Pantalla3(taskId = taskId))
                    }
                )
            }

            // 2. Pantalla secundaria opcional
            entry<Routes.Pantalla2> {
                // Vista secundaria
            }

            // 3. Pantalla de vista de tarea (Solo lectura)
            entry<Routes.Pantalla3> { key ->
                TaskViewScreen(
                    taskId = key.taskId,
                    viewModel = viewModel,
                    onEditClick = {
                        backStack.add(Routes.Pantalla4(taskId = key.taskId))
                    },
                    onDeleteClick = {
                        viewModel.deleteTask(key.taskId)
                        if (backStack.size > 1) {
                            backStack.removeAt(backStack.lastIndex)
                        }
                    },
                    onBackClick = {
                        if (backStack.size > 1) {
                            backStack.removeAt(backStack.lastIndex)
                        }
                    }
                )
            }

            // 4. Pantalla de edición de tarea (TaskDetailScreen)
            entry<Routes.Pantalla4> { key ->
                TaskDetailScreen(
                    taskId = key.taskId,
                    viewModel = viewModel,
                    onBackClick = {
                        if (backStack.size > 1) {
                            backStack.removeAt(backStack.lastIndex)
                        }
                    }
                )
            }
        }
    )
}