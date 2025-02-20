package com.example.sm844

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PhoneDirectoryAdapter(private val users: MutableList<User>) : RecyclerView.Adapter<PhoneDirectoryAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_phone_directory, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        private val departmentTextView: TextView = itemView.findViewById(R.id.departmentTextView)
        private val positionTextView: TextView = itemView.findViewById(R.id.positionTextView)
        private val emailTextView: TextView = itemView.findViewById(R.id.emailTextView)
        private val phoneTextView: TextView = itemView.findViewById(R.id.phoneTextView)

        fun bind(user: User) {
            nameTextView.text = "${user.lastName} ${user.firstName} ${user.middleName}"
            departmentTextView.text = "Отдел: ${user.department}"
            positionTextView.text = "Должность: ${user.position}"
            emailTextView.text = "Email: ${user.email}"
            phoneTextView.text = "Телефон: ${user.phone}"
        }
    }
}