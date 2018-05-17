package com.example.kozjava.smartalarm.sign_in

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import android.os.SystemClock
import android.util.Log
import android.widget.Toast
import com.example.kozjava.smartalarm.models.user.User
import com.example.kozjava.smartalarm.settings.Config
import org.json.JSONObject
import java.io.*
import java.lang.reflect.Method
import java.net.HttpURLConnection
import java.net.URL

import khttp.get
import khttp.post

/**
 * Created by kozjava on 17.05.2018.
 */
class SignInTask(context: Context, user : User) : AsyncTask<Void, Void, String>() {

    private var dialog : ProgressDialog
    private lateinit var user : User
    @SuppressLint("StaticFieldLeak")
    private lateinit var context : Context
    init {
        dialog = ProgressDialog(context)
        this.user = user
        this.context = context
    }

    override fun doInBackground(vararg p0: Void?): String? {

        val payload = mapOf("username" to user.username,
                        "password" to user.passwd)
        val r = post(Config.getServerAddress()+"/reg/registration", data = payload)
        Log.i("TAG", r.text)
        return r.text
    }

    override fun onPreExecute() {
        dialog.setTitle("Немного подождите")
        dialog.setMessage("Попытка входа...")
        dialog.setCancelable(false)
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        dialog.show()
    }

    override fun onPostExecute(result: String?) {
        if(dialog.isShowing) dialog.dismiss()

        Toast.makeText(context, result, Toast.LENGTH_SHORT).show()
    }


}