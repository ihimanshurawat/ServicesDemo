package com.himanshurawat.servicesdemo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.himanshurawat.servicesdemo.services.ForegroundService

class ForegroundServiceActivity: AppCompatActivity() {


    private lateinit var startButton: Button
    private lateinit var stopButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foreground_service)

        startButton = findViewById(R.id.activity_foreground_service_start_button)
        stopButton = findViewById(R.id.activity_foreground_service_stop_button)

        startButton.setOnClickListener{
            if(it != null){
                startService(Intent(this@ForegroundServiceActivity,ForegroundService::class.java))
            }
        }

        stopButton.setOnClickListener{
            if(it != null){
                stopService(Intent(this@ForegroundServiceActivity,ForegroundService::class.java))
            }
        }

    }
}