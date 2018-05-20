package com.example.kozjava.smartalarm.home

import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.widget.Button
import android.widget.Toast
import com.example.kozjava.smartalarm.R
import com.example.kozjava.smartalarm.settings.Config
import khttp.get
import khttp.post
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private lateinit var button : Button
    private lateinit var statusButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        var policy : StrictMode.ThreadPolicy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        button = findViewById<Button>(R.id.button_alarmOn)
        statusButton = findViewById(R.id.button_status)

        button.setOnClickListener({view ->
            var payload = mapOf("username" to "Andrew",
                    "imei" to "867330022257979",
                    "cmd" to "MCELL")
            var result = post(Config.getServerAddress()+"/q/sendCommand", data = payload)

            Toast.makeText(this, result.text, Toast.LENGTH_SHORT)
        })

        statusButton.setOnClickListener({view ->
            var payload = mapOf("imei" to "867330022257979")
            var result = get(Config.getServerAddress()+"/q/readModemResponse",data = payload)

            Toast.makeText(this, result.text, Toast.LENGTH_SHORT)
        })
    }
}
