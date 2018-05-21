package com.example.kozjava.smartalarm.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kozjava.smartalarm.R


class Tab1 : Fragment() {




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.i("TAG", "TAB1")
        val v : View = inflater.inflate(R.layout.tab1, container, false)
        return v
    }
}