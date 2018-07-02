package com.give3.gizrog.give3

import android.content.Context
import android.content.Intent
import android.support.v4.app.ActivityOptionsCompat
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import com.give3.gizrog.give3.models.AppData

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
        initializeDoneButton()
    }

    private fun initializeSectionsButton() {
        val view = findViewById<Button>(R.id.button_sections)
        view.setOnClickListener {
            val sectionsIntent = SectionsActivity.makeIntent(this@MenuActivity)
            val optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    this@MenuActivity,
                    view,
                    getString(R.string.transition_menu))
            window.enterTransition = null
            sectionsIntent.putParcelableArrayListExtra("Sections", appData?.sections)
            startActivityForResult(this, sectionsIntent, RESULT_SECTION, optionsCompat.toBundle())
        }
    }

    private fun initializeTasksButton() {
        val view = findViewById<Button>(R.id.button_tasks)
        view.setOnClickListener {
            val sectionsIntent = TasksActivity.makeIntent(this@MenuActivity)
            val optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    this@MenuActivity,
                    view,
                    getString(R.string.transition_menu))
            window.enterTransition = null
            startActivityForResult(this, sectionsIntent, REQUEST_TASK, optionsCompat.toBundle())
        }
    }

    private fun initializeDoneButton() {
        findViewById<Button>(R.id.button_done).setOnClickListener {
            val assessmentIntent = AssessmentActivity.makeIntent(this@MenuActivity)
            val optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    this@MenuActivity,
                    findViewById(R.id.layout_done),
                    getString(R.string.transition_menu))
            window.enterTransition = null
            startActivityForResult(this, assessmentIntent, REQUEST_TASK, optionsCompat.toBundle())
        }
        updateDoneButtonStatus()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(resultCode) {
            RESULT_SECTION -> appData?.sections = data!!.getParcelableArrayListExtra(KEY_SECTIONS)
            REQUEST_TASK -> appData?.tasks = data!!.getParcelableArrayListExtra(KEY_TASKS)
        }
        updateDoneButtonStatus()
    }

    private fun updateDoneButtonStatus() {
        val button = findViewById<Button>(R.id.button_done)
        button.isEnabled = appData?.isComplete()!!
    }

    companion object {

        const val KEY_SECTIONS = SectionsActivity.KEY_SECTIONS
        const val KEY_TASKS = TasksActivity.KEY_TASKS
        const val RESULT_SECTION = SectionsActivity.RESULT_SECTION
        const val REQUEST_TASK = TasksActivity.RESULT_TASKS

        private val TAG = "MenuActivity"

        fun makeIntent(ctx: Context): Intent {
            return Intent(ctx, MenuActivity::class.java)
        }
    }
}
