package com.himanshurawat.servicesdemo.services

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.himanshurawat.servicesdemo.App
import com.himanshurawat.servicesdemo.ForegroundServiceActivity
import com.himanshurawat.servicesdemo.R

class ForegroundService: Service() {

    private lateinit var mp: MediaPlayer

    override fun onCreate() {
        super.onCreate()
        mp = MediaPlayer.create(this, RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE))
        mp.isLooping = false
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        mp.start()

        val notificationIntent = Intent(this,ForegroundServiceActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this,0,notificationIntent,0)
        val notification: Notification = NotificationCompat.Builder(this, App.CHANNEL_ID)
                .setContentTitle("Hey Service is Working")
                .setContentText("Wah Wah")
                .setContentIntent(pendingIntent)
                .build()
        startForeground(1,notification)

        return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        mp.stop()
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }
}