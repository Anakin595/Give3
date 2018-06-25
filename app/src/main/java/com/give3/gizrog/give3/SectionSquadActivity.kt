package com.give3.gizrog.give3

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import com.give3.gizrog.give3.adapters.SectionListAdapter

import java.util.Objects
import kotlin.collections.ArrayList

class SectionSquadActivity : AppCompatActivity() {

    internal var controlBundle: Bundle? = null

    internal var studentsNames: ArrayList<String> = ArrayList()

    var listView: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_section_squad)

        studentsNames.add("add ...")

        val title = Objects.requireNonNull(intent.extras).getInt("Title")
        studentsNames = intent.extras!!.getStringArrayList("Students")
        val isLast = intent.extras.getBoolean("last")

        listView = findViewById(R.id.listview_section_squad)
        val adapter = SectionListAdapter(this, studentsNames)
        listView?.adapter = adapter
        adapter.notifyDataSetChanged()

        val titleTextView = findViewById<TextView>(R.id.listview_title)
        val secTitle = "Section $title"
        titleTextView.text = secTitle

        val okButton: Button? = findViewById(R.id.button_accept_listview)
        okButton?.setOnClickListener({
            val resultIntent = Intent()
            if(isLast) {
                resultIntent.putExtra(KEY_STUDENTS, studentsNames)
                resultIntent.putExtra("Title", title)
                setResult(RESULT_NEW_ITEM, resultIntent)
            } else {
                resultIntent.putExtra(KEY_STUDENTS, studentsNames)
                setResult(RESULT_UPDATE, resultIntent)
            }
            supportFinishAfterTransition()
        })

    }

    companion object {

        const val KEY_STUDENTS = "Students"
        const val RESULT_NEW_ITEM = 1
        const val RESULT_UPDATE = 2



        fun makeIntent(context: Context, bundle: Bundle): Intent {
            return Intent(context, SectionSquadActivity::class.java)
        }
    }

}
