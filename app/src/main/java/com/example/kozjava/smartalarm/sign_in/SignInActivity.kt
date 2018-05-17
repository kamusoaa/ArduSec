package com.example.kozjava.smartalarm.sign_in

import android.animation.Animator
import android.annotation.TargetApi
import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.content.ContextCompat
import android.view.WindowManager
import com.example.kozjava.smartalarm.R
import com.romainpiel.shimmer.Shimmer
import com.romainpiel.shimmer.ShimmerTextView

class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        changeStatusColorBar()
        var shimmer : Shimmer = Shimmer()
        var tv : ShimmerTextView = findViewById(R.id.shimmer_tv)
        shimmer.setDuration(7000)
                .setStartDelay(2000)
                .setDirection(Shimmer.ANIMATION_DIRECTION_LTR)
                .setAnimatorListener(object : Animator.AnimatorListener {
                    override fun onAnimationRepeat(p0: Animator?) {
                    }

                    override fun onAnimationEnd(p0: Animator?) {
                    }

                    override fun onAnimationCancel(p0: Animator?) {
                    }

                    override fun onAnimationStart(p0: Animator?) {
                    }
                })
                .start(tv)
    }

    private fun changeStatusColorBar(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = this.resources.getColor(R.color.sign_primary)
        }
    }
}
