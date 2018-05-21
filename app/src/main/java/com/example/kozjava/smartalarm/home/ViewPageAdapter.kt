package com.example.kozjava.smartalarm.home

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class ViewPageAdapter (fm: FragmentManager, internal var Titles: Array<CharSequence>, internal var NumbOfTabs: Int) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return if(position == 0)
            Tab1()
        else
            Tab2()

    }

    override fun getCount(): Int {
        return NumbOfTabs
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return Titles[position]
    }
}