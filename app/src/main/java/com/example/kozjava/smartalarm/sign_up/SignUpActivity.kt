package com.example.kozjava.smartalarm.sign_up

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import com.example.kozjava.smartalarm.R

class SignUpActivity : AppCompatActivity() {

    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var phone: EditText
    private lateinit var signUpButton: Button
    var isAnimated = false

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        phone = findViewById(R.id.sign_up_phone)
        signUpButton = findViewById(R.id.sign_up_button)
        password = findViewById(R.id.sign_up_password)
        username = findViewById(R.id.sign_up_username)
        phone.visibility = View.INVISIBLE

        val editTextAnimation = AnimationUtils.loadAnimation(this, R.anim.edittextanimation)
        val buttonAnimation = AnimationUtils.loadAnimation(this, R.anim.buttonanimation)
        buttonAnimation.fillAfter = true
        editTextAnimation.fillAfter = true

        editTextAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(p0: Animation?) {
            }

            override fun onAnimationEnd(p0: Animation?) {
                phone.visibility = View.VISIBLE
            }

            override fun onAnimationStart(p0: Animation?) {
            }

        })
        buttonAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(p0: Animation?) {
            }

            override fun onAnimationEnd(p0: Animation?) {
                signUpButton.y = signUpButton.y + 150F
            }

            override fun onAnimationStart(p0: Animation?) {
            }

        })
        password.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (!isAnimated) {
                    if (p0!!.length >= 8) {
                        phone.startAnimation(editTextAnimation)
                        signUpButton.startAnimation(buttonAnimation)
                        isAnimated = true
                    }
                }
            }

        })




        signUpButton.setOnClickListener({ view ->
            if (password.text.length < 8) {
                val alertDialog = AlertDialog.Builder(this).setTitle("Ошибка")
                        .setMessage("Пароль должен содержать 8 и более символов")
                        .setPositiveButton("OK",
                                { dialogInterface, i -> })
                alertDialog.show()
            } else {
                SignUpTask(this, username.text.toString(),
                        phone.text.toString(),
                        password.text.toString()).execute()
            }
        })
    }
}
