package com.example.sm844

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddUserActivity : AppCompatActivity() {
    private lateinit var lastNameEditText: EditText
    private lateinit var firstNameEditText: EditText
    private lateinit var middleNameEditText: EditText
    private lateinit var departmentEditText: EditText
    private lateinit var positionEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var loginEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var confirmPasswordEditText: EditText
    private lateinit var roleRadioGroup: RadioGroup
    private lateinit var addUserButton: Button
    private lateinit var userManager: UserManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)

        lastNameEditText = findViewById(R.id.lastNameEditText)
        firstNameEditText = findViewById(R.id.firstNameEditText)
        middleNameEditText = findViewById(R.id.middleNameEditText)
        departmentEditText = findViewById(R.id.departmentEditText)
        positionEditText = findViewById(R.id.positionEditText)
        emailEditText = findViewById(R.id.emailEditText)
        phoneEditText = findViewById(R.id.phoneEditText)
        loginEditText = findViewById(R.id.loginEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText)
        roleRadioGroup = findViewById(R.id.roleRadioGroup)
        addUserButton = findViewById(R.id.addUserButton)
        userManager = UserManager(this)

        addUserButton.setOnClickListener {
            val lastName = lastNameEditText.text.toString()
            val firstName = firstNameEditText.text.toString()
            val middleName = middleNameEditText.text.toString()
            val department = departmentEditText.text.toString()
            val position = positionEditText.text.toString()
            val email = emailEditText.text.toString()
            val phone = phoneEditText.text.toString()
            val login = loginEditText.text.toString()
            val password = passwordEditText.text.toString()
            val confirmPassword = confirmPasswordEditText.text.toString()
            val isAdmin = roleRadioGroup.checkedRadioButtonId == R.id.radioAdmin

            if (lastName.isNotBlank() && firstName.isNotBlank() && department.isNotBlank() && position.isNotBlank() && email.isNotBlank() && login.isNotBlank() && password == confirmPassword) {
                userManager.addUser(User(login, password, isAdmin, lastName, firstName, middleName, department, position, email, phone))
                Toast.makeText(this, "Пользователь добавлен", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Пожалуйста, заполните все обязательные поля правильно", Toast.LENGTH_SHORT).show()
            }
        }
    }
}