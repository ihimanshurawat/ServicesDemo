package com.himanshurawat.servicesdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.himanshurawat.servicesdemo.services.BoundService
import kotlinx.android.synthetic.main.activity_bound_service.*
import android.os.IBinder
import android.content.ComponentName
import android.content.Context
import android.content.ServiceConnection
import android.os.Bundle


class BoundServiceActivity: AppCompatActivity() {

    private lateinit var boundService: BoundService
    private var isServiceBound = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bound_service)

        activity_bound_service_print_timestamp_button.setOnClickListener {
            if(isServiceBound){
                activity_bound_service_timestamp_text_view.text = boundService.getTimestamp()
            }
        }


        activity_bound_service_bind_button.setOnClickListener {
            val bind = Intent(applicationContext, BoundService::class.java)
            bindService(bind,mServiceConnection, Context.BIND_AUTO_CREATE)
        }

        activity_bound_service_unbind_button.setOnClickListener {
            if(isServiceBound){
                unbindService(mServiceConnection)
                isServiceBound = false
            }
        }
    }


    private val mServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName) {
            isServiceBound = false
        }

        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            val myBinder = service as BoundService.MyBinder
            boundService = myBinder.getService()
            isServiceBound = true
        }
    }
}