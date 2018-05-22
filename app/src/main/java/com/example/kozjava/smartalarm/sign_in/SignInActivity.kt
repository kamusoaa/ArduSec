package com.example.kozjava.smartalarm.sign_in

import android.animation.Animator
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import com.example.kozjava.smartalarm.R
import com.example.kozjava.smartalarm.home.HomeActivity
import com.example.kozjava.smartalarm.models.user.User
import com.example.kozjava.smartalarm.settings.PrefManager
import com.example.kozjava.smartalarm.sign_up.SignUpActivity
import com.romainpiel.shimmer.Shimmer
import com.romainpiel.shimmer.ShimmerTextView

class SignInActivity : AppCompatActivity() {

    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var signInButton: Button
    private lateinit var linkToReg: ShimmerTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        changeStatusColorBar()
        var shimmer: Shimmer = Shimmer()
        var prefManager = PrefManager(this)
        if (prefManager.username.isNotEmpty())
            launchHomeScreen()


        username = findViewById(R.id.sign_in_username)
        password = findViewById(R.id.sign_in_password)
        signInButton = findViewById(R.id.sign_in_button)
        linkToReg = findViewById(R.id.shimmer_tv)

        signInButton.setOnClickListener {
            onButtonClick()
            Log.i("TAG", "meh")
        }

        linkToReg.setOnClickListener {
            var intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

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
                .start(linkToReg)

    }


    private fun changeStatusColorBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = this.resources.getColor(R.color.sign_primary)
        }
    }

    private fun launchHomeScreen() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    private fun onButtonClick() {
        if (username.text.isNotEmpty() && password.text.isNotBlank()) {
            var user = User()
            user.username = username.text.toString()
            user.passwd = password.text.toString()
            SignInTask(this, user).execute()
        } else {
            val alertDialog = AlertDialog.Builder(this).setTitle("Ошибка")
                    .setMessage("Проверьте введенные данные")
                    .setPositiveButton("OK",
                            { dialogInterface, i -> })
            alertDialog.show()
        }
    }
}



