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

class SectionSquadActivity : AppCompatActivity() {

    private lateinit var section: Section
    private var listView: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_section_squad)

        section = intent.getParcelableExtra(KEY_SECTION)
        val lastIndex: Int = intent.getIntExtra("LastIndex", 0)

        initializeListView()
        setTitleText(section.id, lastIndex)

        findViewById<Button>(R.id.button_accept_listview).setOnClickListener {
            val resultIntent = Intent()
            val bundle = Bundle()
            if(section.id == 0) {
                section.id = lastIndex + 1
                section.title = section.id.toString()
                setResult(RESULT_NEW_SECTION, resultIntent)
            } else {
                setResult(RESULT_UPDATE_SECTION, resultIntent)
            }
            bundle.putParcelable(KEY_SECTION, section)
            resultIntent.putExtras(bundle)
            supportFinishAfterTransition()
        }
    }

    private fun setTitleText(id: Int, lastId: Int) {
        val titleTextView = findViewById<TextView>(R.id.listview_title)
        titleTextView.text  = if(id == 0) {
            "New section (${lastId + 1})"
        } else {
            "Section $id"
        }
    }

    private fun initializeListView() {
        listView = findViewById(R.id.listview_section_squad)
        val adapter = SectionListAdapter(this, section.studentsNames)
        listView?.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    companion object {

        const val KEY_SECTION = "Section"
        const val KEY_REQUEST_CODE = "RequestCode"
        const val RESULT_NEW_SECTION = 1
        const val RESULT_UPDATE_SECTION = 0



        fun makeIntent(context: Context): Intent {
            return Intent(context, SectionSquadActivity::class.java)
        }
    }

}
