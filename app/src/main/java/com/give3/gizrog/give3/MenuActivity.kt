package com.give3.gizrog.give3

import android.content.Context
import android.content.Intent
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.util.Log
import android.view.View
import android.widget.TextView

import com.bignerdranch.expandablerecyclerview.Model.ParentObject
import com.give3.gizrog.give3.items.Task

import java.util.ArrayList

class MenuActivity : AppCompatActivity() {

    private val sections = ArrayList<Section>()
    private val tasks = ArrayList<Task>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        Log.d(TAG, "onCreate: started.")

        initializeSectionsButton()
        initializeTasksButton()


    }

    private fun initializeSectionsButton() {
        val view = findViewById<TextView>(R.id.textView_section)
        view.setOnClickListener {
            val sectionsIntent = SectionsActivity.makeIntent(this@MenuActivity)

            val optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    this@MenuActivity,
                    findViewById(R.id.textView_section),
                    "fromMenu")
            window.enterTransition = null

            ActivityCompat.startActivityForResult(this, sectionsIntent, 1, optionsCompat.toBundle())
        }
    }

    private fun initializeTasksButton() {
        val view = findViewById<TextView>(R.id.textView_tasks)
        view.setOnClickListener { }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

    }

    companion object {

        private val TAG = "MenuActivity"


        fun makeIntent(ctx: Context): Intent {
            return Intent(ctx, MenuActivity::class.java)
        }
    }
}
