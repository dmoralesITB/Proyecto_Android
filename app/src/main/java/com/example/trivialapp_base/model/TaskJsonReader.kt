package com.example.trivialapp_base.model

import android.content.Context
import kotlinx.serialization.json.Json

object TaskJsonReader {
    fun readTasks(context: Context, fileName: String = "task.json"): List<Task> {
        val json = context.assets.open(fileName)
            .bufferedReader()
            .use { it.readText() }
        return Json.decodeFromString(json)
    }
}