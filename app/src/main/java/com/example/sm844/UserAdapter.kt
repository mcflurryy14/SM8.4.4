package com.example.sm844

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(private val users: MutableList<User>, private val userManager: UserManager) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.bind(user)
        holder.deleteUserButton.setOnClickListener {
            userManager.deleteUser(user.login)
            users.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, itemCount)
        }
    }

    override fun getItemCount(): Int {
        return users.size
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val loginTextView: TextView = itemView.findViewById(R.id.loginTextView)
        private val passwordTextView: TextView = itemView.findViewById(R.id.passwordTextView)
        val deleteUserButton: Button = itemView.findViewById(R.id.deleteUserButton)

        fun bind(user: User) {
            loginTextView.text = "${user.lastName} ${user.firstName} ${user.middleName}"
            passwordTextView.text = "*".repeat(user.password.length) // Пароль отображается звездочками
        }
    }
}