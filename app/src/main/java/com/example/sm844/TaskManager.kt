package com.example.sm844

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object TaskManager {
    private lateinit var sharedPreferences: SharedPreferences
    private val gson = Gson()
    private val tasksAndNotifications = mutableListOf<TaskOrNotification>()
    private val archive = mutableListOf<TaskOrNotification>()
    private var nextId = 0

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences("TaskPreferences", Context.MODE_PRIVATE)
        loadTasksAndNotifications()
        loadArchive()
    }

    fun getNextId(): String {
        return String.format("%03d", nextId++)
    }

    fun addTaskOrNotification(taskOrNotification: TaskOrNotification) {
        tasksAndNotifications.add(taskOrNotification)
        saveTasksAndNotifications()
    }

    fun getCurrentTasksAndNotifications(): MutableList<TaskOrNotification> {
        return tasksAndNotifications
    }

    fun getArchiveTasksAndNotifications(): MutableList<TaskOrNotification> {
        return archive
    }

    fun archiveTaskOrNotification(taskOrNotification: TaskOrNotification) {
        tasksAndNotifications.remove(taskOrNotification)
        if (!archive.contains(taskOrNotification)) {
            archive.add(taskOrNotification)
        }
        saveTasksAndNotifications()
        saveArchive()
    }

    fun saveTasksAndNotifications() {
        try {
            val editor = sharedPreferences.edit()
            val json = gson.toJson(tasksAndNotifications)
            editor.putString("tasksAndNotifications", json)
            editor.apply()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun loadTasksAndNotifications() {
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

    fun saveArchive() {
        try {
            val editor = sharedPreferences.edit()
            val json = gson.toJson(archive)
            editor.putString("archive", json)
            editor.apply()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun loadArchive() {
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
}