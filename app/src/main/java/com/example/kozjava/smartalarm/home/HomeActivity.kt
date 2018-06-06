package com.example.kozjava.smartalarm.home

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.StrictMode
import android.support.design.widget.Snackbar
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.Toast
import com.example.kozjava.smartalarm.R


class HomeActivity : AppCompatActivity() {

    lateinit var toolbar : Toolbar
    lateinit var viewPager: ViewPager
    lateinit var adapter: ViewPageAdapter
    lateinit var tabs : SlidingTabLayout
    var titles = arrayOf<CharSequence>("Сигнализация", "Меню")
    var numOfTabs : Int = 2
    companion object {
        var count : Boolean = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        setContentView(R.layout.activity_home)

        toolbar = findViewById(R.id.tool_bar)
        setSupportActionBar(toolbar)

        adapter = ViewPageAdapter(supportFragmentManager, titles,numOfTabs)
        adapter.setContext(this)
        viewPager = findViewById(R.id.pager)
        viewPager.adapter = adapter

        tabs = findViewById(R.id.tabs)
        tabs.setDistributeEvenly(true)
        tabs.setCustomTabColorizer { resources.getColor(R.color.selector) }
        tabs.setViewPager(viewPager)




        startService(Intent(this, AlarmService::class.java))
    }

    override fun onBackPressed() {
        if(!count){
            Snackbar.make(window.decorView, "Нажмите еще раз для выхода", Snackbar.LENGTH_SHORT).show()
            count = true
        }
        else
        {
            count = false
            super.onBackPressed()
        }

    }
}
