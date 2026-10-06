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
    val backStack = rememberNavBackStack(Routes.Pantalla1)

    NavDisplay(
        backStack = backStack,
        onBack = {
            if (backStack.size > 1) {
                backStack.removeAt(backStack.lastIndex)
            }
        },
        entryProvider = entryProvider {
            // 1. Pantalla principal
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

            // 3. Pantalla de detalle
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