package com.example.kozjava.smartalarm.slider

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.text.Html
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.example.kozjava.smartalarm.R
import com.example.kozjava.smartalarm.home.HomeActivity
import com.example.kozjava.smartalarm.settings.PrefManager
import com.example.kozjava.smartalarm.sign_in.SignInActivity

class SliderActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
    private lateinit var dotsLayout: LinearLayout
    private lateinit var mySliderViewPageAdapter: SliderViewPageAdapter

    private lateinit var dots: Array<TextView?>
    private lateinit var layouts: IntArray

    private lateinit var btnSkip: Button
    private lateinit var btnNext: Button
    private lateinit var prefManager: PrefManager
    private lateinit var typeface: Typeface


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        prefManager = PrefManager(this)
        if (!prefManager.isFirstTimeLaunch) {
            launchSignInScreen()
            finish()
        }

        if (Build.VERSION.SDK_INT >= 21) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }

        setContentView(R.layout.activity_slider)
        viewPager = findViewById(R.id.view_pager)
        dotsLayout = findViewById(R.id.layoutDots)
        btnSkip = findViewById(R.id.btn_skip)
        btnNext = findViewById(R.id.btn_next)

        layouts = intArrayOf(R.layout.intro_slide1,
                R.layout.intro_slide2,
                R.layout.intro_slide3,
                R.layout.intro_slide4)
        addBottomDots(0)
        changeStatusColorBar()


        mySliderViewPageAdapter = SliderViewPageAdapter(this, layouts)
        viewPager.adapter = mySliderViewPageAdapter
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener)


        btnSkip.setOnClickListener { view -> launchSignInScreen() }
        btnNext.setOnClickListener { view ->
            var current: Int = getItem(+1)
            if (current < layouts.size)
                viewPager.setCurrentItem(current)
            else
                launchSignInScreen()
        }
    }

    private fun addBottomDots(currentPage: Int) {
        dots = arrayOfNulls(layouts.size)
        var colorActive: IntArray = resources.getIntArray(R.array.array_dot_active)
        var colorInactive: IntArray = resources.getIntArray(R.array.array_dot_inactive)
        dotsLayout.removeAllViews()
        for (i in dots.indices) {
            dots[i] = TextView(this)
            dots[i]?.setText(Html.fromHtml("&#8226;"))
            dots[i]?.setTextSize(35F)
            dots[i]?.setTextColor(colorInactive[currentPage])
            dotsLayout.addView(dots[i])
        }

        if (dots.size > 0)
            dots[currentPage]?.setTextColor(colorActive[currentPage])

    }

    private fun changeStatusColorBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    private fun launchSignInScreen() {
        prefManager.isFirstTimeLaunch = false
        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
    }

    private fun launchHomeScreen() {
        prefManager.isFirstTimeLaunch = false
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    private fun getItem(i: Int): Int {
        return viewPager.currentItem + i
    }

    internal var viewPagerPageChangeListener: ViewPager.OnPageChangeListener = object : ViewPager.OnPageChangeListener {

        override fun onPageSelected(position: Int) {
            addBottomDots(position)

            if (position == layouts.size - 1) {
                btnNext.text = "Круто"
                btnSkip.visibility = View.GONE
            } else {
                btnNext.text = "Дальше"
                btnSkip.visibility = View.VISIBLE
            }
        }

        override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {

        }

        override fun onPageScrollStateChanged(arg0: Int) {

        }
    }
}

