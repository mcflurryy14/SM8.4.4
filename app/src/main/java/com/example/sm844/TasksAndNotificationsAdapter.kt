package com.example.sm844

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView

class TasksAndNotificationsAdapter(private val tasksAndNotifications: MutableList<TaskOrNotification>) :
    RecyclerView.Adapter<TasksAndNotificationsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val idTextView: TextView = itemView.findViewById(R.id.idTextView)
        val typeTextView: TextView = itemView.findViewById(R.id.typeTextView)
        val topicTextView: TextView = itemView.findViewById(R.id.topicTextView)
        val contentTextView: TextView = itemView.findViewById(R.id.contentTextView)
        val authorTextView: TextView = itemView.findViewById(R.id.authorTextView)
        val statusSpinner: Spinner = itemView.findViewById(R.id.statusSpinner)
        val deleteButton: Button = itemView.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task_or_notification, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val taskOrNotification = tasksAndNotifications[position]
        holder.idTextView.text = taskOrNotification.id
        holder.typeTextView.text = taskOrNotification.type
        holder.topicTextView.text = taskOrNotification.topic
        holder.contentTextView.text = taskOrNotification.content
        holder.authorTextView.text = "Автор: ${taskOrNotification.authorLogin}"
        val statusOptions = if (taskOrNotification.type == "Задача") {
            arrayOf("Не назначено", "Принято в работу", "В ожидании", "Решено")
        } else {
            arrayOf("Актуально", "Не актуально")
        }
        val adapter = ArrayAdapter(holder.itemView.context, android.R.layout.simple_spinner_item, statusOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        holder.statusSpinner.adapter = adapter
        holder.statusSpinner.setSelection(statusOptions.indexOf(taskOrNotification.status))

        holder.statusSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                taskOrNotification.status = statusOptions[position]
                if (taskOrNotification.type == "Задача" && taskOrNotification.status == "Решено" ||
                    taskOrNotification.type == "Оповещение" && taskOrNotification.status == "Не актуально") {
                    TaskManager.archiveTaskOrNotification(taskOrNotification)
                    notifyItemRemoved(holder.adapterPosition)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        holder.deleteButton.setOnClickListener {
            tasksAndNotifications.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    override fun getItemCount() = tasksAndNotifications.size
}