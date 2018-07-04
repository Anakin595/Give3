package com.give3.gizrog.give3

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.give3.gizrog.give3.models.Section
import com.give3.gizrog.give3.models.Task

class AssessmentActivity : AppCompatActivity() {

    private lateinit var sections: ArrayList<Section>
    private lateinit var tasks: ArrayList<Task>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assessment)

        sections = intent.getParcelableArrayListExtra(KEY_SECTIONS)
        tasks = intent.getParcelableArrayListExtra(KEY_TASKS)

    }

    companion object {

        const val KEY_SECTIONS: String = MenuActivity.KEY_SECTIONS
        const val KEY_TASKS: String = MenuActivity.KEY_TASKS

        fun makeIntent(context: Context): Intent {
            return Intent(context, AssessmentActivity::class.java)
        }
    }
}
