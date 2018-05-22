package com.example.kozjava.smartalarm.home

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kozjava.smartalarm.R
import com.example.kozjava.smartalarm.models.RecyclerData

class MainMenu : Fragment() {

    lateinit var myContext: Context

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.i("TAG", "TAB2")
        val view: View = inflater.inflate(R.layout.tab2, container, false)
        return view
    }

    fun setContext(context: Context) {
        this.myContext = context
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        var recData = ArrayList<RecyclerData>()

        recData.add(RecyclerData(R.drawable.siren_on, "Включить сирену", "on"))
        recData.add(RecyclerData(R.drawable.siren_off, "Выключить сирену", "off"))
        recData.add(RecyclerData(R.drawable.motion_stmoi, "Датчик движения", "stmoi"))
        recData.add(RecyclerData(R.drawable.sound_stsnd, "Датчик звука", "stsnd"))
        recData.add(RecyclerData(R.drawable.proximity_stprx, "Датчик препятствия", "stprx"))
        recData.add(RecyclerData(R.drawable.hall_sthll, "Датчик открытия", "sthll"))
        recData.add(RecyclerData(R.drawable.all_sensor_stall, "Выключить все датчики", "stall"))
        recData.add(RecyclerData(R.drawable.module_id, "ID модема", "modid"))
        recData.add(RecyclerData(R.drawable.module_revision, "Версия модема", "mrevn"))
        recData.add(RecyclerData(R.drawable.cell_operator, "Оператор сети", "mcell"))
        recData.add(RecyclerData(R.drawable.status, "Сетевой статус", "mstat"))
        recData.add(RecyclerData(R.drawable.signal_strength, "Уровень сигнала", "msign"))
        recData.add(RecyclerData(R.drawable.voltage, "Напряжение", "mvolt"))
        recData.add(RecyclerData(R.drawable.adc, "Сила тока", "madcv"))
        recData.add(RecyclerData(R.drawable.balance, "Баланс", "mball"))
        recData.add(RecyclerData(R.drawable.trafic, "Трафик", "mtraf"))


        val myRecyclerViewAdapter: RecyclerView = view.findViewById(R.id.recyclerview)
        val recyclerViewAdapter = RecyclerViewAdapter(myContext, recData)
        myRecyclerViewAdapter.layoutManager = GridLayoutManager(myContext, 3)
        myRecyclerViewAdapter.adapter = recyclerViewAdapter


    }
}