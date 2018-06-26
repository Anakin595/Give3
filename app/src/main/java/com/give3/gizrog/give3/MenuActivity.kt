package com.give3.gizrog.give3

import android.content.Context
import android.content.Intent
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.util.Log
import android.widget.TextView
import com.give3.gizrog.give3.models.AppData

import com.give3.gizrog.give3.models.Section
import com.give3.gizrog.give3.models.Task

import java.util.ArrayList

class MenuActivity : BaseAppCompactActivity() {


    private val appData: AppData? = AppData(ArrayList(), ArrayList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        Log.d(TAG, "onCreate: started.")

        initialize()

    }

    private fun initialize() {
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
                    getString(R.string.transition_menu))
            window.enterTransition = null
            sectionsIntent.putParcelableArrayListExtra("Sections", appData?.sections)
            startActivityForResult(this, sectionsIntent, REQUEST_SECTION, optionsCompat.toBundle())
        }
    }

    private fun initializeTasksButton() {
        val view = findViewById<TextView>(R.id.textView_tasks)
        view.setOnClickListener {
            val sectionsIntent = TasksActivity.makeIntent(this@MenuActivity)
            val optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    this@MenuActivity,
                    findViewById(R.id.textView_tasks),
                    getString(R.string.transition_menu))
            window.enterTransition = null
            startActivityForResult(this, sectionsIntent, REQUEST_TASK, optionsCompat.toBundle())}
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
//        when(requestCode) {
//            REQUEST_SECTION -> handleSection()
//            REQUEST_TASK -> handleTasks()
//        }
    }

    companion object {

        const val REQUEST_SECTION = 1
        const val REQUEST_TASK = 2

        private val TAG = "MenuActivity"

        fun makeIntent(ctx: Context): Intent {
            return Intent(ctx, MenuActivity::class.java)
        }
    }
}
