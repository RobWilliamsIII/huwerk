package com.example.huwerk

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class HostActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_host)


        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, HostWelcomeFragment())
            .commit()
    }
}
