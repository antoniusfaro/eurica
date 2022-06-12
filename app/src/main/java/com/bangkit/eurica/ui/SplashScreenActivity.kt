package com.bangkit.eurica

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Intent(this@SplashScreenActivity, LoginActivity::class.java).also {
            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(it)
                finish()
            }, 3000)
        }

    }
}