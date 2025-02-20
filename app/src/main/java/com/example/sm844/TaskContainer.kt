package com.example.sm844

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TaskContainer(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("TaskPreferences", Context.MODE_PRIVATE)
    private val gson = Gson()
    val tasksAndNotifications = mutableListOf<TaskOrNotification>()
    val archive = mutableListOf<TaskOrNotification>()

    init {
        loadTasksAndNotifications()
        loadArchive()
    }

    private fun saveTasksAndNotifications() {
        try {
            val editor = sharedPreferences.edit()
            val json = gson.toJson(tasksAndNotifications)
            editor.putString("tasksAndNotifications", json)
            editor.apply()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun loadTasksAndNotifications() {
        try {
            val json = sharedPreferences.getString("tasksAndNotifications", null)
            if (json != null) {
                val type = object : TypeToken<MutableList<TaskOrNotification>>() {}.type
                val loadedTasksAndNotifications: MutableList<TaskOrNotification> = gson.fromJson(json, type)
                tasksAndNotifications.clear()
                tasksAndNotifications.addAll(loadedTasksAndNotifications)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun saveArchive() {
        try {
            val editor = sharedPreferences.edit()
            val json = gson.toJson(archive)
            editor.putString("archive", json)
            editor.apply()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun loadArchive() {
        try {
            val json = sharedPreferences.getString("archive", null)
            if (json != null) {
                val type = object : TypeToken<MutableList<TaskOrNotification>>() {}.type
                val loadedArchive: MutableList<TaskOrNotification> = gson.fromJson(json, type)
                archive.clear()
                archive.addAll(loadedArchive)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun addTaskOrNotification(taskOrNotification: TaskOrNotification) {
        tasksAndNotifications.add(taskOrNotification)
        saveTasksAndNotifications()
    }

    fun archiveTaskOrNotification(taskOrNotification: TaskOrNotification) {
        tasksAndNotifications.remove(taskOrNotification)
        if (!archive.contains(taskOrNotification)) {
            archive.add(taskOrNotification)
        }
        saveTasksAndNotifications()
        saveArchive()
    }
}