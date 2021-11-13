package com.test.mykado.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.test.mykado.R

class SplashScreenActivity : AppCompatActivity() {

    private var handler: Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        handler = Handler()
        handler!!.postDelayed({ this.toDashboard() }, 1000)

    }

    private fun toDashboard() {
        startActivity(Intent(this, DashboardActivity::class.java))
    }


}