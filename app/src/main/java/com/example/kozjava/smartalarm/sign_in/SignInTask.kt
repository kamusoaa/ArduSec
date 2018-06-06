package com.example.kozjava.smartalarm.sign_in

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AlertDialog
import android.util.Log
import com.example.kozjava.smartalarm.home.HomeActivity
import com.example.kozjava.smartalarm.models.Response
import com.example.kozjava.smartalarm.models.user.User
import com.example.kozjava.smartalarm.settings.Config
import com.example.kozjava.smartalarm.settings.PrefManager
import com.google.gson.Gson
import khttp.post

/**
 * Created by kozjava on 17.05.2018.
 */
class SignInTask(context: Context, user : User, activity: Activity) : AsyncTask<Void, Void, String>() {

    private var dialog : ProgressDialog
    private lateinit var user : User
    private lateinit var prefManager : PrefManager
    private lateinit var activity: Activity

    @SuppressLint("StaticFieldLeak")
    private lateinit var context : Context
    init {
        dialog = ProgressDialog(context)
        this.user = user
        this.context = context
        this.activity = activity
    }

    override fun doInBackground(vararg p0: Void?): String? {

        val payload = mapOf("username" to user.username,
                        "password" to user.passwd)
        val r = post(Config.getServerAddress()+"/reg/login", data = payload)
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
        var gson = Gson()
        var response = gson?.fromJson(result, Response::class.java)
        if(response.code.equals("404")){
            val alertDialog = AlertDialog.Builder(context).setTitle("Ошибка")
                    .setMessage(response.response)
                    .setPositiveButton("OK",
                            {dialogInterface, i ->  })
            alertDialog.show()
        }
        else{
            prefManager = PrefManager(context)
            prefManager.username = user.username
            var intent = Intent(context, HomeActivity::class.java)
            context.startActivity(intent)
            activity.finish()
        }
    }


}