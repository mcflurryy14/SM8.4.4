package com.example.sm844

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        val userClass = intent.getStringExtra("userClass") ?: "user"

        findViewById<Button>(R.id.btnPhoneDirectory).setOnClickListener {
            val intent = Intent(this, PhoneDirectoryActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.btnTasksAndNotifications).setOnClickListener {
            val intent = Intent(this, TasksAndNotificationsActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.btnMeetingRooms).setOnClickListener {
            // Запуск активности Переговорные комнаты
        }

        findViewById<Button>(R.id.btnAdministration).setOnClickListener {
            val intent = Intent(this, AdministrationActivity::class.java)
            intent.putExtra("userClass", userClass)
            startActivity(intent)
        }
    }
}