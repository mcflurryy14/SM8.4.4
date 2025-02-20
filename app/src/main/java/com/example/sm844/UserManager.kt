package com.example.sm844

import android.content.Context
import android.content.SharedPreferences

class UserManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)

    init {
        if (sharedPreferences.getString("user_Admin", null) == null) {
            addUser(User("Admin", "Admin", true, "Admin", "Admin"))
        }

        if (sharedPreferences.getString("user_root", null) == null) {
            addUser(User("root", "root", true, "Root", "User"))
        }
    }

    fun addUser(user: User) {
        val editor = sharedPreferences.edit()
        editor.putString("user_${user.login}", "${user.password}|${if (user.isAdmin) "admin" else "user"}|${user.lastName}|${user.firstName}|${user.middleName}|${user.department}|${user.position}|${user.email}|${user.phone}")
        editor.apply()
    }

    fun deleteUser(login: String) {
        val editor = sharedPreferences.edit()
        editor.remove("user_$login")
        editor.apply()
    }

    fun getUser(login: String): User? {
        val userString = sharedPreferences.getString("user_$login", null) ?: return null
        val parts = userString.split("|")
        if (parts.size < 9) {
            return null
        }
        return User(login, parts[0], parts[1] == "admin", parts[2], parts[3], parts[4], parts[5], parts[6], parts[7], parts[8])
    }

    fun getAllUsers(): List<User> {
        val allUsers = sharedPreferences.all
        val userList = mutableListOf<User>()
        for ((key, value) in allUsers) {
            if (key.startsWith("user_")) {
                val login = key.removePrefix("user_")
                val parts = (value as String).split("|")
                if (parts.size >= 9) {
                    userList.add(User(login, parts[0], parts[1] == "admin", parts[2], parts[3], parts[4], parts[5], parts[6], parts[7], parts[8]))
                }
            }
        }
        return userList
    }

    fun getCurrentUser(): User? {
        val currentUserLogin = sharedPreferences.getString("current_user_login", null) ?: return null
        return getUser(currentUserLogin)
    }
}