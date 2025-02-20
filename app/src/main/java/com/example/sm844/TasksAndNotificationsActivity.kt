package com.example.sm844

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import androidx.viewpager2.widget.ViewPager2

class TasksAndNotificationsActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var addTaskOrNotificationButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasks_and_notifications)

        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)
        addTaskOrNotificationButton = findViewById(R.id.addTaskOrNotificationButton)

        val adapter = TasksAndNotificationsPagerAdapter(this)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = if (position == 0) "Актуальное" else "Архив"
        }.attach()

        addTaskOrNotificationButton.setOnClickListener {
            val dialog = AddTaskOrNotificationDialogFragment()
            dialog.show(supportFragmentManager, "AddTaskOrNotificationDialogFragment")
        }
    }
}