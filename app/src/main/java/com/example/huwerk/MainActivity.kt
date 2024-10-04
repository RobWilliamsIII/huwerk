package com.example.huwerk

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.FirebaseApp

class MainActivity : AppCompatActivity() {

    //  Firebase authentication and UI elements
    private lateinit var auth: FirebaseAuth
    private lateinit var userTypeGroup: RadioGroup
    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var registerButton: Button
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Firebase app
        FirebaseApp.initializeApp(this)

        //  Firebase authentication for user sign-ins
        auth = FirebaseAuth.getInstance()


        setContentView(R.layout.activity_main)

        // UI mapped to layouts
        emailInput = findViewById(R.id.email)
        passwordInput = findViewById(R.id.password)
        registerButton = findViewById(R.id.registerButton)
        loginButton = findViewById(R.id.loginButton)
        userTypeGroup = findViewById(R.id.userTypeGroup)

        // Register a new user
        registerButton.setOnClickListener {
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()

            // Host or Guest user
            val userType = getUserType()


            registerUser(email, password, userType)
        }

        // Login an already registered user
        loginButton.setOnClickListener {
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()
            val userType = getUserType()


            loginUser(email, password, userType)
        }
    }

    // Determine if user is guest or host
    private fun getUserType(): String {
        return when (userTypeGroup.checkedRadioButtonId) {
            R.id.radioHost -> "host"
            else -> "guest"
        }
    }

    // register a new user using Firebase
    private fun registerUser(email: String, password: String, userType: String) {

        if (email.isNotEmpty() && password.isNotEmpty()) {

            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->

                if (task.isSuccessful) {
                    Toast.makeText(this, "Registered", Toast.LENGTH_SHORT).show()

                    // Go to Guest or Host Welcome screen
                    navigateToWelcomeFragment(userType)
                } else {

                    Toast.makeText(this, "Registration failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                }
            }
        } else {

            Toast.makeText(this, "enter email and password", Toast.LENGTH_SHORT).show()
        }
    }

    // log in an existing firebase user
    private fun loginUser(email: String, password: String, userType: String) {

        if (email.isNotEmpty() && password.isNotEmpty()) {
            // sign in an existing user
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->

                if (task.isSuccessful) {
                    Toast.makeText(this, "Logged in", Toast.LENGTH_SHORT).show()
                    navigateToWelcomeFragment(userType)
                } else {

                    Toast.makeText(this, "Login failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                }
            }
        } else {

            Toast.makeText(this, "enter email and password", Toast.LENGTH_SHORT).show()
        }
    }

    // Take user to guest or host screen
    private fun navigateToWelcomeFragment(userType: String) {

        val intent = if (userType == "host") {
            Intent(this, HostActivity::class.java)
        } else {
            Intent(this, GuestActivity::class.java)
        }
        startActivity(intent)
        finish()
    }
}
