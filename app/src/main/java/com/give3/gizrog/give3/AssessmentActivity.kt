package com.give3.gizrog.give3

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.util.Log
import android.widget.Button
import android.widget.ExpandableListView
import android.widget.Toast
import com.give3.gizrog.give3.adapters.AssessmentExpandableListAdapter
import com.give3.gizrog.give3.models.AssessmentSection
import com.give3.gizrog.give3.models.Section
import com.give3.gizrog.give3.models.Task
import com.give3.gizrog.give3.services.ExcelService

class AssessmentActivity : AppCompatActivity() {

    private var assessmentSections: ArrayList<AssessmentSection> = ArrayList()
    private lateinit var expandableListView: ExpandableListView
    private lateinit var adapter: AssessmentExpandableListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assessment)

        setAppData()
        initializeExpandableListViewAdapter()
        setTaskCheckBoxListener()
        initializeExportButton()

    }

    private fun setAppData() {
        val sections: ArrayList<Section> = intent.getParcelableArrayListExtra(KEY_SECTIONS)
        val tasks: ArrayList<Task> = intent.getParcelableArrayListExtra(KEY_TASKS)
        sections.forEach {
            val taskSub = ArrayList<Task>()
            tasks.forEach { taskSub.add(it.copy()) }
            val assessmentSection = AssessmentSection(it, 2f, taskSub)
            assessmentSections.add(assessmentSection)
        }
    }

    private fun initializeExpandableListViewAdapter() {
        expandableListView = findViewById(R.id.expandlist_assessment)
        adapter = AssessmentExpandableListAdapter(this, assessmentSections)
        expandableListView.setAdapter(adapter)
        adapter.notifyDataSetChanged()
    }

    private fun setTaskCheckBoxListener() {
        expandableListView.setOnChildClickListener { parent, view, groupPosition, childPosition, id ->
            assessmentSections[groupPosition].tasks[childPosition].isComplete = !assessmentSections[groupPosition].tasks[childPosition].isComplete
            adapter.parentItemList[groupPosition].grade = adapter.parentItemList[groupPosition].calculateGrade()
            adapter.notifyDataSetChanged()
            false
        }
    }

    private fun initializeExportButton() {
        findViewById<Button>(R.id.button_assessment_export).setOnClickListener {
            val permissionGranted: Boolean = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
            if(permissionGranted) {
                Log.d("buttonExport", "export: permission granted.")
                ExcelService.exportAssessmentSectionsToCsv(assessmentSections)
                Toast.makeText(this, "File saved.", Toast.LENGTH_LONG).show()
            } else {
                Log.d("buttonExport", "export: ask permission.")
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 200)
            }

        }
    }

    companion object {

        const val KEY_SECTIONS: String = MenuActivity.KEY_SECTIONS
        const val KEY_TASKS: String = MenuActivity.KEY_TASKS

        fun makeIntent(context: Context): Intent {
            return Intent(context, AssessmentActivity::class.java)
        }
    }
}
