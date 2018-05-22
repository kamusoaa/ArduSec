package com.example.kozjava.smartalarm.home

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.kozjava.smartalarm.R
import io.realm.Realm


class MainPanel() : Fragment() {

    lateinit var realm: Realm
    lateinit var myContext: Context
    lateinit var topText: TextView
    lateinit var imageView: ImageView
    lateinit var signalingButton: Button
    lateinit var bottomText: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v: View = inflater.inflate(R.layout.tab1, container, false)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        topText = view.findViewById<TextView>(R.id.tab1_top_text)
        imageView = view.findViewById<ImageView>(R.id.tab1_imageView)
        signalingButton = view.findViewById<Button>(R.id.tab1_button)
        bottomText = view.findViewById<TextView>(R.id.tab1_bottom_text)
        if (true) {
            topText.text = "Сигнализация включена"
            imageView.setImageResource(R.drawable.home_on)
            bottomText.text = "Нажмите, чтобы выключить сигнализацию"
            signalingButton.text = "Выключить"

        } else {
            topText.text = "Сигнализация выключена"
            imageView.setImageResource(R.drawable.home_off)
            bottomText.text = "Нажмите, чтобы включить сигнализацию"
            signalingButton.text = "Включить"
        }


        /*
        signalingButton.setOnClickListener {
            if(res[0]!!.isAlarmOn){
                alarmOff()
            }
            else{
                alarmOn()
            }
        }*/
    }

    fun setContext(context: Context) {
        this.myContext = context
    }
}