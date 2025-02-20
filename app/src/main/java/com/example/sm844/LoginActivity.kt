package com.example.sm844

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var loginEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var userManager: UserManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        userManager = UserManager(this)

        loginEditText = findViewById(R.id.loginEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.loginButton)

        loginButton.setOnClickListener {
            val login = loginEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (login == "root" && password == "root") {
                val intent = Intent(this, MainMenuActivity::class.java)
                intent.putExtra("userClass", "admin")
                startActivity(intent)
            } else {
                val user = userManager.getUser(login)
                if (user != null && user.password == password) {
                    val intent = Intent(this, MainMenuActivity::class.java)
                    intent.putExtra("userClass", if (user.isAdmin) "admin" else "user")
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Неверный логин или пароль", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}