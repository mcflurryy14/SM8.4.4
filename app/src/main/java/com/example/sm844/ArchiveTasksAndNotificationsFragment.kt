package com.example.sm844

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ArchiveTasksAndNotificationsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var tasksAndNotificationsAdapter: TasksAndNotificationsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_archive_tasks_and_notifications, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(context)
        tasksAndNotificationsAdapter = TasksAndNotificationsAdapter(TaskManager.getArchiveTasksAndNotifications().toMutableList())
        recyclerView.adapter = tasksAndNotificationsAdapter

        return view
    }
}