package com.give3.gizrog.give3

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class TasksActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasks)
    }

    companion object {

        const val RESULT_TASKS: Int = 0
        const val KEY_TASKS: String = "Tasks"

        fun makeIntent(ctx: Context): Intent {
            return Intent(ctx, TasksActivity::class.java)
        }
    }
}
