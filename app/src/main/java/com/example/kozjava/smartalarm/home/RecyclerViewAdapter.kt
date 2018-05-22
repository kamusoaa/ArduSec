package com.example.kozjava.smartalarm.home

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.os.AsyncTask
import android.os.Handler
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.kozjava.smartalarm.R
import com.example.kozjava.smartalarm.R.drawable.status
import com.example.kozjava.smartalarm.models.RecyclerData
import com.example.kozjava.smartalarm.models.Response
import com.example.kozjava.smartalarm.settings.Config
import com.google.gson.Gson
import com.google.gson.JsonObject
import khttp.get
import khttp.post
import org.json.JSONObject
import java.util.*


class RecyclerViewAdapter(val context: Context, val items: ArrayList<RecyclerData>) : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View
        val mInflater: LayoutInflater = LayoutInflater.from(context)
        view = mInflater.inflate(R.layout.cardview_item, parent, false)

        return MyViewHolder(view)
    }


    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.sensorName.text = items[position].name
        holder.image.setImageResource(items[position].thumbnail)

        var state: Boolean = false

        var dialog = ProgressDialog(context)
        dialog.setTitle("Немного подождите")
        dialog.setMessage("Отправка запроса")
        dialog.setCancelable(false)
        dialog.progress = ProgressDialog.STYLE_SPINNER

        holder.cardView.setOnClickListener { v: View? ->
            dialog.show()
            if (SendCommand().execute(items[position].command).get() == "ok") {
                val handler: Handler = Handler()
                val timer = Timer()
                val task: TimerTask = object : TimerTask() {
                    override fun run() {
                        handler.post(object : Runnable {
                            override fun run() {
                                var response: Response = ReadModemResponse().execute().get()
                                if (response.code == "201") {
                                    val tv: TextView = dialog.findViewById(android.R.id.message)
                                    tv.text = ""
                                    var message: String = response.response
                                    dialog.setMessage(message)
                                } else {
                                    dialog.dismiss()
                                    timer.cancel()
                                    val alertDialog = AlertDialog.Builder(context)
                                            .setTitle("Ответ")
                                            .setMessage(response.response)
                                            .setPositiveButton("OK", { dialogInterface, i -> })
                                    alertDialog.show()
                                }

                            }

                        })
                    }

                }
                timer.schedule(task, 5000, 10000)
            } else {
                dialog.dismiss()
                val alertDialog = AlertDialog.Builder(context)
                        .setTitle("Ошибка")
                        .setMessage("Произошла ошибка при отправке команды. Попробуйте позже")
                        .setPositiveButton("OK", { dialogInterface, i -> })
                alertDialog.show()
            }

        }
    }


    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var sensorName: TextView
        var image: ImageView
        var cardView: CardView

        init {
            sensorName = itemView.findViewById(R.id.tab2_title)
            image = itemView.findViewById(R.id.tab2_image)
            cardView = itemView.findViewById(R.id.cardview)
        }
    }

    class SendCommand : AsyncTask<String, Void, String>() {

        override fun doInBackground(vararg params: String): String {

            var payload = mapOf("username" to "Andrew",
                    "imei" to "867330022257979",
                    "cmd" to params[0])
            val response = post(Config.getServerAddress() + "/q/sendCommand", data = payload)

            val jsonRoot = JSONObject(response.text)
            val data: String = jsonRoot.getString("cmd")
            Log.i("TAG", data)
            return data
        }
    }

    class ReadModemResponse : AsyncTask<Void, Void, Response>() {
        override fun doInBackground(vararg params: Void?): Response? {
            val payload = mapOf("imei" to "867330022257979")
            val response = get(Config.getServerAddress() + "/q/readModemResponse", params = payload)
            Log.i("TAG", "Response " + response.text)
            val gson = Gson()
            val data = gson.fromJson<Response>(response.text, Response::class.java)
            Log.i("TAG", "Data " + data.response)
            return data
        }

    }
}

