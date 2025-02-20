package com.example.sm844

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.DialogFragment

class AddTaskOrNotificationDialogFragment : DialogFragment() {

    private lateinit var taskRadioButton: RadioButton
    private lateinit var notificationRadioButton: RadioButton
    private lateinit var topicEditText: EditText
    private lateinit var contentEditText: EditText
    private lateinit var submitButton: Button
    private lateinit var userManager: UserManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_task_or_notification, container, false)
        taskRadioButton = view.findViewById(R.id.taskRadioButton)
        notificationRadioButton = view.findViewById(R.id.notificationRadioButton)
        topicEditText = view.findViewById(R.id.topicEditText)
        contentEditText = view.findViewById(R.id.contentEditText)
        submitButton = view.findViewById(R.id.submitButton)
        userManager = UserManager(requireContext())

        submitButton.setOnClickListener {
            val type = if (taskRadioButton.isChecked) "Задача" else if (notificationRadioButton.isChecked) "Оповещение" else ""
            val topic = topicEditText.text.toString().trim()
            val content = contentEditText.text.toString().trim()
            val currentUser = userManager.getCurrentUser()

            Log.d("AddTaskOrNotification", "Type: $type")
            Log.d("AddTaskOrNotification", "Topic: $topic")
            Log.d("AddTaskOrNotification", "Content: $content")
            Log.d("AddTaskOrNotification", "CurrentUser: $currentUser")

            if (type.isNotEmpty() && topic.isNotEmpty() && content.isNotEmpty()) {
                val taskOrNotification = TaskOrNotification(
                    id = TaskManager.getNextId(),
                    type = type,
                    topic = topic,
                    content = content,
                    authorLogin = currentUser?.login ?: "Unknown",
                    status = if (type == "Задача") "Не назначено" else "Актуально"
                )
                TaskManager.addTaskOrNotification(taskOrNotification)
                Log.d("AddTaskOrNotification", "Task or Notification added: $taskOrNotification")
                dismiss()
            } else {
                Log.d("AddTaskOrNotification", "All fields not filled")
                Toast.makeText(context, "Заполните все поля", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }
}