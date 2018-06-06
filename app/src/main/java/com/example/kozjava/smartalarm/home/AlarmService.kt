package com.example.kozjava.smartalarm.home

import android.app.Notification
import android.app.Service
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.os.IBinder
import android.support.v4.app.NotificationCompat
import android.util.Log
import com.example.kozjava.smartalarm.R

class AlarmService : Service() {

    private lateinit var builder : NotificationCompat.Builder

    override fun onBind(p0: Intent?): IBinder {
        return null!!
    }

    override fun onCreate() {
        super.onCreate()

        Log.i("TAG", "Service Start")


        builder = NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.icon)
                .setContentTitle("Сигнализация включена")
        var notification = Notification()
        notification.flags = notification.flags or Notification.FLAG_SHOW_LIGHTS
        notification = builder.build()


        var alarmManager = AlarmManager()
        alarmManager.mContext = this
        alarmManager.builder = builder
        alarmManager.startManager()

        startForeground(666, notification)

    }

    override fun onDestroy() {
        super.onDestroy()
    }
}