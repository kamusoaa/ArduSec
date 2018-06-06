package com.example.kozjava.smartalarm.home

import android.app.AlarmManager
import android.app.Notification
import android.app.Notification.*
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Context.POWER_SERVICE
import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import android.os.Environment
import android.os.PowerManager
import android.support.v4.app.NotificationCompat
import android.util.Log
import com.example.kozjava.smartalarm.R
import com.example.kozjava.smartalarm.models.Response
import com.example.kozjava.smartalarm.settings.Config
import com.google.gson.Gson
import khttp.get
import android.os.Environment.getExternalStorageDirectory




class AlarmManager() : BroadcastReceiver(){


    public var mContext : Context? = null
    public var builder : NotificationCompat.Builder? = null


    override fun onReceive(p0: Context?, p1: Intent?) {
        ServiceManager(p0!!).execute()
    }

    fun startManager(){
        Log.i("TAG", "Start Manager")
        val manager = mContext!!.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(mContext, com.example.kozjava.smartalarm.home.AlarmManager::class.java)
        val pendingIntent = PendingIntent.getBroadcast(mContext,0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 60000, pendingIntent)

    }




    class ServiceManager(context: Context) : AsyncTask<Void, Void, String>(){

        var mContext: Context
        init {
            this.mContext = context
        }
        override fun doInBackground(vararg p0: Void?): String {
            val jsonResponse = get(Config.getServerAddress()+"/q/getAlarmStatus");
            Log.i("TAG", "Response : " + jsonResponse.text)
            return jsonResponse.text
        }

        override fun onPostExecute(result: String?) {



            val gson = Gson()
            val response = gson.fromJson(result, Response::class.java)




            if(response.code.equals("200")){

                val powerManager = mContext.getSystemService(POWER_SERVICE) as PowerManager
                var isScreenOn : Boolean = powerManager.isScreenOn()

                if(!isScreenOn) {
                    val wl = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK or PowerManager.ACQUIRE_CAUSES_WAKEUP or PowerManager.ON_AFTER_RELEASE, "MyLock")
                    wl.acquire(10000)
                    val wl_cpu = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "MyCpuLock")
                    wl_cpu.acquire(10000)
                }


                val intent = Intent(mContext, HomeActivity::class.java)
                val pendingIntent = PendingIntent.getActivity(mContext, 0, intent, 0)

                var builder = NotificationCompat.Builder(mContext)
                builder.setSmallIcon(R.mipmap.ic_launcher_round)
                builder.setContentTitle("Сирена включена")
                builder.setContentText("Сработал датчик " + response.response)
                builder.setDefaults(DEFAULT_SOUND or DEFAULT_VIBRATE or DEFAULT_LIGHTS)
                builder.addAction(R.mipmap.ic_launcher, "Открыть", pendingIntent)



                val notification = builder.build()
                val notificationManager = mContext.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

                notificationManager.notify(1, notification)

            }
        }
    }

}