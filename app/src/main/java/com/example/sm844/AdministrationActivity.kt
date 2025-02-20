package com.example.sm844

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AdministrationActivity : AppCompatActivity() {
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userManager: UserManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_administration)

        userManager = UserManager(this)

        // Получение класса пользователя из интента
        val userClass = intent.getStringExtra("userClass")

        // Проверка прав администратора
        if (!isAdminUser(userClass)) {
            showAccessDeniedMessage()
            return
        }

        findViewById<Button>(R.id.addUserButton).setOnClickListener {
            val intent = Intent(this, AddUserActivity::class.java)
            startActivity(intent)
        }

        userRecyclerView = findViewById(R.id.userRecyclerView)
        userRecyclerView.layoutManager = LinearLayoutManager(this)
        userRecyclerView.adapter = UserAdapter(userManager.getAllUsers().toMutableList(), userManager)
    }

    private fun isAdminUser(userClass: String?): Boolean {
        return userClass == "admin" || userClass == "Администратор"
    }

    private fun showAccessDeniedMessage() {
        Toast.makeText(this, "Отказано в доступе", Toast.LENGTH_SHORT).show()
        finish()
    }
}