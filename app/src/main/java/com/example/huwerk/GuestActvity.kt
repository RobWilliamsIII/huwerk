package com.example.huwerk

import GuestWelcomeFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class GuestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest)


        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, GuestWelcomeFragment())
            .commit()
    }
}
