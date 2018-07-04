package com.give3.gizrog.give3

import android.content.Context
import android.content.Intent
import android.support.v4.app.ActivityOptionsCompat
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
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
            sectionsIntent.putParcelableArrayListExtra(KEY_SECTIONS, appData?.sections)
            startActivityForResult(this, sectionsIntent, RESULT_SECTION, null)
        }
    }

    private fun initializeTasksButton() {
        val view = findViewById<Button>(R.id.button_tasks)
        view.setOnClickListener {
            val sectionsIntent = TasksActivity.makeIntent(this@MenuActivity)
            sectionsIntent.putParcelableArrayListExtra(KEY_TASKS, appData?.tasks)
            startActivityForResult(this, sectionsIntent, REQUEST_TASK, null)
        }
    }

    private fun initializeDoneButton() {
        findViewById<Button>(R.id.button_section_done).setOnClickListener {
            val assessmentIntent = AssessmentActivity.makeIntent(this@MenuActivity)
            val sectionsAssessment =
            assessmentIntent.putParcelableArrayListExtra(KEY_SECTIONS, appData?.sections)
            assessmentIntent.putParcelableArrayListExtra(KEY_TASKS, appData?.tasks)
            startActivityForResult(this, assessmentIntent, REQUEST_DONE, null)
        }
        updateActivityLayoutStatus()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(resultCode) {
            RESULT_SECTION -> appData?.sections = data!!.getParcelableArrayListExtra(KEY_SECTIONS)
            REQUEST_TASK -> appData?.tasks = data!!.getParcelableArrayListExtra(KEY_TASKS)
        }
        updateActivityLayoutStatus()
    }

    private fun updateActivityLayoutStatus() {
        val button = findViewById<Button>(R.id.button_section_done)
        button.isEnabled = appData?.isComplete()!!
        val sectionsCountTextView: TextView = findViewById(R.id.text_count_sections)
        sectionsCountTextView.text = appData.sections.size.toString()
        val tasksCountTextView: TextView = findViewById(R.id.text_count_tasks)
        tasksCountTextView.text = appData.tasks.size.toString()
    }

    companion object {

        const val KEY_SECTIONS = SectionsActivity.KEY_SECTIONS
        const val KEY_TASKS = TasksActivity.KEY_TASKS
        const val RESULT_SECTION = SectionsActivity.RESULT_SECTION
        const val REQUEST_TASK = TasksActivity.RESULT_TASKS
        const val REQUEST_DONE = 10

        private val TAG = "MenuActivity"

        fun makeIntent(ctx: Context): Intent {
            return Intent(ctx, MenuActivity::class.java)
        }
    }
}
