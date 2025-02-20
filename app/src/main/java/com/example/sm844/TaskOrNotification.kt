package com.example.sm844

data class TaskOrNotification(
    val id: String,
    val type: String,
    val topic: String,
    val content: String,
    val authorLogin: String,
    var status: String
)