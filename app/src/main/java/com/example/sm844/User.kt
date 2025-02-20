package com.example.sm844

data class User(
    val login: String,
    val password: String,
    val isAdmin: Boolean,
    val lastName: String,
    val firstName: String,
    val middleName: String = "",
    val department: String = "",
    val position: String = "",
    val email: String = "",
    val phone: String = ""
)