package com.give3.gizrog.give3

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import com.give3.gizrog.give3.adapters.SectionListAdapter
import com.give3.gizrog.give3.models.Section

import kotlin.collections.ArrayList

class SectionSquadActivity : AppCompatActivity() {

    internal var controlBundle: Bundle? = null

    internal var studentsNames: ArrayList<String> = ArrayList()

    private lateinit var section: Section
    private var requestCode: Int? = null

    var listView: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_section_squad)

        section = intent.getParcelableExtra("Section")
        val sectionId = intent.getIntExtra("SectionId",0)
        section.title = "$sectionId"
        requestCode = intent.getIntExtra("RequestCode",1)

        listView = findViewById(R.id.listview_section_squad)
        val adapter = SectionListAdapter(this, section.studentsNames)
        listView?.adapter = adapter
        adapter.notifyDataSetChanged()

        val titleTextView = findViewById<TextView>(R.id.listview_title)
        val secTitle = "Section ${section.title}"
        titleTextView.text = secTitle

        findViewById<Button>(R.id.button_accept_listview).setOnClickListener {
            val resultIntent = Intent()
            val bundle = Bundle()
            bundle.putParcelable(KEY_SECTION, section)
            bundle.putInt("SectionId", sectionId)
            resultIntent.putExtras(bundle)
            if(requestCode == RESULT_NEW_SECTION) {
                setResult(RESULT_NEW_SECTION, resultIntent)
            } else if (requestCode == RESULT_UPDATE_SECTION) {
                setResult(RESULT_UPDATE_SECTION, resultIntent)
            }
            supportFinishAfterTransition()
        }
    }

    companion object {

        const val KEY_SECTION = "Section"
        const val KEY_STUDENTS = "Students"
        const val RESULT_NEW_SECTION = 1
        const val RESULT_UPDATE_SECTION = 0



        fun makeIntent(context: Context, bundle: Bundle): Intent {
            return Intent(context, SectionSquadActivity::class.java)
        }
    }

}
