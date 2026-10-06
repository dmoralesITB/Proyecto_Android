package com.example.trivialapp_base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.trivialapp_base.ui.theme.TrivialAPP_BaseTheme
import com.example.trivialapp_base.viewmodel.TaskViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TrivialAPP_BaseTheme {
                // Instanciamos el ViewModel ligado al ciclo de vida de la Activity
                val taskViewModel: TaskViewModel = viewModel()

                // Punto de entrada de la navegación principal
                NavigationWrapper(viewModel = taskViewModel)
            }
        }
    }
}