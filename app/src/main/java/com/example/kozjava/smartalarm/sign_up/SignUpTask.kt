package com.example.kozjava.smartalarm.sign_up

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.util.Log
import com.example.kozjava.smartalarm.home.HomeActivity
import com.example.kozjava.smartalarm.models.user.Response
import com.example.kozjava.smartalarm.models.user.User
import com.example.kozjava.smartalarm.settings.Config
import com.example.kozjava.smartalarm.settings.PrefManager
import com.google.gson.Gson
import io.realm.Realm
import io.realm.RealmResults
import khttp.post

class SignUpTask(context : Context, name : String, phone : String, pass : String) : AsyncTask<Void, Void, String>() {

    private var dialog : ProgressDialog
    private lateinit var prefManager : PrefManager
    private var name: String
    private var pass: String
    private var phone: String
    @SuppressLint("StaticFieldLeak")
    private var context: Context
    private var realm : Realm



    init {
        this.context = context
        this.dialog = ProgressDialog(context)
        this.name = name
        this.pass = pass
        this.phone = phone
        Realm.init(context)
        this.realm = Realm.getDefaultInstance()
    }

    override fun doInBackground(vararg p0: Void?): String {
        var payload = mapOf("username" to name,
                "password" to pass,
                "phoneNum" to phone)


        var data = post(Config.getServerAddress()+"/reg/registration", data = payload)
        return data.text
    }

    override fun onPostExecute(result: String?) {
        if(dialog.isShowing) dialog.dismiss()

        var gson = Gson()
        var response = gson.fromJson(result, Response::class.java)
        if(response.code.equals("404")){
            val alertDialog = AlertDialog.Builder(context)
                    .setTitle("Ошибка")
                    .setMessage(response.response)
                    .setPositiveButton("OK", {dialogInterface, i ->  })
            alertDialog.show()
        }
        else {
            prefManager = PrefManager(context)
            prefManager.username = name
            realm.beginTransaction()
            var user : User = realm.createObject(User::class.java)
            user.username = name
            user.password = pass
            user.mobilePhone = phone
            realm.commitTransaction()

            var res : RealmResults<User> = realm.where(User::class.java)
                    .findAll();
            Log.i("TAG", res.toString())

            var intent = Intent(context, HomeActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onPreExecute() {
        dialog.setTitle("Немного подождите")
        dialog.setMessage("Регистрация нового пользователя...")
        dialog.setCancelable(false)
        dialog.progress = ProgressDialog.STYLE_SPINNER
        dialog.show()
    }
}