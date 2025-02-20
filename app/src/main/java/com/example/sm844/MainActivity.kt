package com.example.sm844

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Инициализация TaskManager
        TaskManager.init(this)
    }

    override fun onPause() {
        super.onPause()
        TaskManager.saveTasksAndNotifications()
        TaskManager.saveArchive()
    }
}