package com.give3.gizrog.give3

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ExpandableListView
import android.widget.Toast
import com.give3.gizrog.give3.adapters.AssessmentExpandableListAdapter
import com.give3.gizrog.give3.models.AssessmentSection
import com.give3.gizrog.give3.models.Section
import com.give3.gizrog.give3.models.Task

class AssessmentActivity : AppCompatActivity() {

    private var assessmentSections: ArrayList<AssessmentSection> = ArrayList()
    private lateinit var expandableListView: ExpandableListView
    private lateinit var adapter: AssessmentExpandableListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assessment)

        setAppData()
        initializeExpandableListViewAdapter()

        expandableListView.setOnChildClickListener { parent, view, groupPosition, childPosition, id ->
            toast("dsffdfssdf!!!")
            assessmentSections[groupPosition].tasks[childPosition].isComplete = !assessmentSections[groupPosition].tasks[childPosition].isComplete
            adapter.notifyDataSetChanged()
             false
        }
    }

    private fun setAppData() {
        val sections: ArrayList<Section> = intent.getParcelableArrayListExtra(KEY_SECTIONS)
        val tasks: ArrayList<Task> = intent.getParcelableArrayListExtra(KEY_TASKS)
        sections.forEach {
            val sub = tasks.subList(0, tasks.lastIndex+1)
            val list = ArrayList(sub)
            val assessmentSection = AssessmentSection(it, 2f, list)
            assessmentSections.add(assessmentSection) // TODO: check,if tasks are copied for each section
        }
    }

    private fun initializeExpandableListViewAdapter() {
        expandableListView = findViewById(R.id.expandlist_assessment)
        adapter = AssessmentExpandableListAdapter(this, assessmentSections)
        expandableListView.setAdapter(adapter)
        adapter.notifyDataSetChanged()
    }

    private fun Context.toast(message: CharSequence) =
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    companion object {

        const val KEY_SECTIONS: String = MenuActivity.KEY_SECTIONS
        const val KEY_TASKS: String = MenuActivity.KEY_TASKS

        fun makeIntent(context: Context): Intent {
            return Intent(context, AssessmentActivity::class.java)
        }
    }
}
