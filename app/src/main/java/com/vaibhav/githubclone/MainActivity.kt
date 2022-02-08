package com.vaibhav.githubclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    lateinit var username: EditText
    lateinit var getDetailsButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        username = findViewById(R.id.username)
        getDetailsButton = findViewById(R.id.getDetails)

        getDetailsButton.setOnClickListener {

            val usernameValue = username.text

            if (usernameValue.isEmpty()) {
                username.error = "Please enter a username!"
            } else {
                val intent = Intent(this, ProfileActivity::class.java)
                intent.putExtra("username", usernameValue)
                startActivity(intent)
            }
        }
    }
}