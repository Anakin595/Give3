package com.give3.gizrog.give3

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setupStartButton()
    }

    private fun setupStartButton() {
        val btn = findViewById<Button>(R.id.button_start)
        btn.setOnClickListener {
            val sectionsIntent = MenuActivity.makeIntent(this@HomeActivity)
            startActivity(sectionsIntent)
        }
    }

    companion object {

        private val TAG = "MenuActivity"


        fun makeIntent(ctx: Context): Intent {
            return Intent(ctx, HomeActivity::class.java)
        }
    }
}
