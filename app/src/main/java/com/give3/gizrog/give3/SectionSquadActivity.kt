package com.give3.gizrog.give3

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import com.give3.gizrog.give3.adapters.SectionListAdapter
import com.give3.gizrog.give3.models.Section

class SectionSquadActivity : AppCompatActivity() {

    private lateinit var section: Section
    private var listView: ListView? = null
    private lateinit var adapter: SectionListAdapter
    private var requestCode: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_section_squad)

        section = intent.getParcelableExtra(KEY_SECTION)
        val lastIndex: Int = intent.getIntExtra("LastIndex", 0)
        requestCode = intent.getIntExtra(KEY_REQUEST_CODE, 0)

        initializeListView()
        initializeAddPersonButton()
        initializeDoneButton()

        setTitleText(section.id, lastIndex)

    }

    private fun initializeListView() {
        listView = findViewById(R.id.listview_section_squad)
        adapter = SectionListAdapter(this, section.studentsNames)
        listView?.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun initializeAddPersonButton() {
        findViewById<Button>(R.id.button_section_squad_add).setOnClickListener {
            adapter.add("New...")
        }
    }

    private fun initializeDoneButton() {
        findViewById<Button>(R.id.button_section_squad_done).setOnClickListener {
            if(section.studentsNames.isEmpty()) {
                Toast.makeText(applicationContext, "Empty section? Come on...", Toast.LENGTH_LONG).show()
            } else {
                val resultIntent = Intent()
                val bundle = Bundle()
                when(requestCode) {
                    NEW_SECTION -> setResult(NEW_SECTION)
                    UPDATE_SECTION -> setResult(UPDATE_SECTION)
                }
                bundle.putParcelable(KEY_SECTION, section)
                resultIntent.putExtras(bundle)
                supportFinishAfterTransition()
            }
        }
    }

    private fun setTitleText(id: Int, lastId: Int) {
        val titleTextView = findViewById<TextView>(R.id.text_section_title)
        titleTextView.text  = if(id == 0) {
            "New section (${lastId + 1})"
        } else {
            "Section $id"
        }
    }

    companion object {

        const val KEY_SECTION = "Section"
        const val KEY_REQUEST_CODE = "RequestCode"
        const val NEW_SECTION = 1
        const val UPDATE_SECTION = 2
        const val RESULT_NEW_SECTION = 1
        const val RESULT_UPDATE_SECTION = 0



        fun makeIntent(context: Context): Intent {
            return Intent(context, SectionSquadActivity::class.java)
        }
    }

}
