package com.example.kozjava.smartalarm.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kozjava.smartalarm.R

class Tab2 : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.i("TAG","TAB2")
        val view : View = inflater.inflate(R.layout.tab2, container, false)
        return view
    }
}