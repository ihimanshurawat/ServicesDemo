package com.himanshurawat.servicesdemo.services

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.os.SystemClock
import android.widget.Chronometer

class BoundService: Service() {

    private val binder = MyBinder()
    private lateinit var chronometer: Chronometer


    override fun onCreate() {
        super.onCreate()
        chronometer = Chronometer(this)
        chronometer.base = SystemClock.elapsedRealtime()
        chronometer.start()
    }

    override fun onBind(p0: Intent?): IBinder {
        return binder
    }

    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
    }

    override fun onUnbind(intent: Intent?): Boolean {
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        chronometer.stop()
    }

    fun getTimestamp(): String {
        val elapsedMillis = SystemClock.elapsedRealtime() - chronometer.base
        val hours = (elapsedMillis / 3600000).toInt()
        val minutes = (elapsedMillis - hours * 3600000).toInt() / 60000
        val seconds = (elapsedMillis - (hours * 3600000).toLong() - (minutes * 60000).toLong()).toInt() / 1000
        val millis = (elapsedMillis - (hours * 3600000).toLong() - (minutes * 60000).toLong() - (seconds * 1000).toLong()).toInt()
        return "$hours : $minutes : $seconds : $millis"
    }


     inner class MyBinder: Binder(){
        fun getService(): BoundService{
            return this@BoundService
        }
    }
}
