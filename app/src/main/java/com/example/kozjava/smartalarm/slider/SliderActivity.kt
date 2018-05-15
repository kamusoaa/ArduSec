package com.example.kozjava.smartalarm.slider

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.View
import android.widget.Button
import com.example.kozjava.smartalarm.R

class SliderActivity : AppCompatActivity() {

    private lateinit var viewPager : ViewPager
    private lateinit var mySliderViewPageAdapter: SliderViewPageAdapter
    private var dots : IntArray? = null
    private lateinit var btnSkip : Button
    private lateinit var btnNext : Button
    private lateinit var prefManager: PrefManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // Checking for first time launch - before calling setContentView()
        prefManager = PrefManager(this)
        if (!prefManager.isFirstTimeLaunch) {
            //launchHomeScreen()
            finish()
        }

        if (Build.VERSION.SDK_INT >= 21) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }

        setContentView(R.layout.activity_slider)

    }
}
