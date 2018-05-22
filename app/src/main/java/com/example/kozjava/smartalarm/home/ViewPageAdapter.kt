package com.example.kozjava.smartalarm.home

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class ViewPageAdapter(fm: FragmentManager, internal var Titles: Array<CharSequence>, internal var NumbOfTabs: Int) : FragmentStatePagerAdapter(fm) {

    lateinit var myContext: Context
    override fun getItem(position: Int): Fragment {
        return if (position == 0) {
            var tab1 = MainPanel()
            tab1.setContext(myContext)
            return tab1


        } else {
            var tab2 = MainMenu()
            tab2.setContext(myContext)
            return tab2

        }

    }

    override fun getCount(): Int {
        return NumbOfTabs
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return Titles[position]
    }

    fun setContext(context: Context) {
        this.myContext = context
    }
}