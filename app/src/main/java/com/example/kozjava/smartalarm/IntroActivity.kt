package com.example.kozjava.smartalarm

import android.content.Intent
import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock.sleep
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import com.example.kozjava.smartalarm.slider.SliderActivity

class IntroActivity : AppCompatActivity() {


    private lateinit var textView : TextView
    private lateinit var imageView: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        val typeface = Typeface.createFromAsset(assets, "font/TitilliumRegular.otf")

        textView = findViewById<TextView>(R.id.text)
        textView.setTypeface(typeface)
        imageView = findViewById<ImageView>(R.id.image)

        val animation = AnimationUtils.loadAnimation(this, R.anim.mytransition)
        textView.startAnimation(animation)
        imageView.startAnimation(animation)
        val intent = Intent(this, SliderActivity::class.java)
        Thread(){
            try {
                sleep(2000)
            }catch (ex : Exception){println(ex)}
            finally {
                startActivity(intent)
                finish()
            }

        }.start()
    }
}
