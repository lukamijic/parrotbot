package com.parrotbot.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.parrotbot.R
import com.parrotbot.parrotbotlib.service.ParrotBotService

class ParrotBotActivity : AppCompatActivity() {

    private val parrotBotServiceIntent by lazy { Intent(this, ParrotBotService::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
    }

    override fun onStart() {
        super.onStart()
        startService(parrotBotServiceIntent)
    }

    override fun onStop() {
        stopService(parrotBotServiceIntent)
        super.onStop()
    }
}
