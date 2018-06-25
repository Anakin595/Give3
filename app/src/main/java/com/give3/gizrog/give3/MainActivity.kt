package com.give3.gizrog.give3

import android.content.Intent
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation

class MainActivity : AppCompatActivity() {



    companion object {
        private val WELCOME_SCREEN_TIME = 1300
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler().postDelayed({
            val homeIntent = HomeActivity.makeIntent(this@MainActivity)
            startActivity(homeIntent)
            finish()
        }, WELCOME_SCREEN_TIME.toLong())

    }

}
