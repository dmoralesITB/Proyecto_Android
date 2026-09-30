package com.example.trivialapp_base

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.trivialapp_base.view.TaskDetailScreen
import com.example.trivialapp_base.view.TaskListScreen
import com.example.trivialapp_base.viewmodel.TaskViewModel

@Composable
fun NavigationWrapper(viewModel: TaskViewModel) {
    // Es fundamental tipar <Routes> para que Navigation 3 use el serializador correcto
    val backStack = rememberNavBackStack<Routes>(Routes.TaskListScreen)

    NavDisplay(
        backStack = backStack,
        onBack = {
            if (backStack.size > 1) {
                backStack.removeAt(backStack.lastIndex)
            }
        },
        entryProvider = entryProvider {
            // 1. Lista de tareas
            entry<Routes.TaskListScreen> {
                TaskListScreen(
                    viewModel = viewModel,
                    onTaskClick = { taskId ->
                        backStack.add(Routes.Pantalla3(taskId = taskId))
                    }
                )
            }

            // 2. Vista secundaria opcional
            entry<Routes.TaskDetailScreen> {
                // ...
            }

            // 3. Detalle de tarea
            entry<Routes.Pantalla3> { key ->
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