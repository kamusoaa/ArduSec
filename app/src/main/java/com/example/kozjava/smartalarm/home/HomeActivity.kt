package com.example.kozjava.smartalarm.home

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import com.example.kozjava.smartalarm.R

class HomeActivity : AppCompatActivity() {

    lateinit var toolbar : Toolbar
    lateinit var viewPager: ViewPager
    lateinit var adapter: ViewPageAdapter
    lateinit var tabs : SlidingTabLayout
    var titles = arrayOf<CharSequence>("Сигнализация", "Меню")
    var numOfTabs : Int = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        toolbar = findViewById(R.id.tool_bar)
        setSupportActionBar(toolbar)


        adapter = ViewPageAdapter(supportFragmentManager, titles,numOfTabs)
        viewPager = findViewById(R.id.pager)
        viewPager.adapter = adapter

        tabs = findViewById(R.id.tabs)
        tabs.setDistributeEvenly(true)
        tabs.setCustomTabColorizer { resources.getColor(R.color.selector) }
        tabs.setViewPager(viewPager)
    }
}
