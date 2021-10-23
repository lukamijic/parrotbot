package com.parrotbot.activity

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity
import com.parrotbot.R
import com.parrotbot.parrotbotlib.service.ParrotBotService

class ParrotBotActivity : AppCompatActivity() {

    private val connection = object : ServiceConnection {

        override fun onServiceConnected(componentName: ComponentName, binder: IBinder) { }

        override fun onServiceDisconnected(componentName: ComponentName) { }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
    }

    override fun onStart() {
        super.onStart()
        bindService(Intent(this, ParrotBotService::class.java), connection, Context.BIND_AUTO_CREATE)
    }

    override fun onStop() {
        unbindService(connection)
        super.onStop()
    }
}
