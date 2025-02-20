package com.example.sm844

import android.content.Context
import android.content.SharedPreferences
import com.example.sm844.models.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class UserStore(private val context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("UserStore", Context.MODE_PRIVATE)
    private val gson = Gson()
    private val userType = object : TypeToken<MutableList<User>>() {}.type
    private var users: MutableList<User>

    init {
        val usersJson = sharedPreferences.getString("users", null)
        users = if (usersJson != null) {
            gson.fromJson(usersJson, userType)
        } else {
            mutableListOf(
                User("admin", "admin", "admin"),
                User("User", "User", "user")
            )
        }
    }

    fun getAllUsers(): List<User> {
        return users
    }

    fun addUser(username: String, password: String, role: String) {
        users.add(User(username, password, role))
        saveUsers()
    }

    fun validateCredentials(username: String, password: String): Boolean {
        return users.any { it.username == username && it.password == password }
    }

    fun getUserRole(username: String?): String {
        return users.find { it.username == username }?.role ?: "user"
    }

    private fun saveUsers() {
        val usersJson = gson.toJson(users)
        sharedPreferences.edit().putString("users", usersJson).apply()
    }
}