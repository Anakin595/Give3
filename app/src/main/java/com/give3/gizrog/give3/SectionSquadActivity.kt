package com.give3.gizrog.give3

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.*
import com.give3.gizrog.give3.adapters.SectionListAdapter
import com.give3.gizrog.give3.models.Section
import kotlinx.android.synthetic.main.activity_section_squad.*

class SectionSquadActivity : AppCompatActivity(), AdapterView.OnItemClickListener {

    private lateinit var section: Section
    private lateinit var listView: ListView
    private lateinit var adapter: SectionListAdapter
    private var requestCode: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_section_squad)

        section = intent.getParcelableExtra(KEY_SECTION)
        requestCode = intent.getIntExtra(KEY_REQUEST_CODE, 0)

        initialize()
    }

    private fun initialize() {
        initializeListView()
        initializeAddPersonButton()
        initializeDoneButton()
        setTitleText()
    }

    private fun initializeListView() {
        listView = findViewById(R.id.listview_section_squad)
        adapter = SectionListAdapter(this, section.studentsNames)
        listView.onItemClickListener = this
        listView.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun initializeAddPersonButton() {
        findViewById<Button>(R.id.button_section_squad_add).setOnClickListener {
            section.studentsNames.add("Name...")
            adapter.notifyDataSetChanged()
        }
    }

    private fun initializeDoneButton() {
        findViewById<Button>(R.id.button_section_squad_done).setOnClickListener {
            if(section.studentsNames.isEmpty()) {
                Toast.makeText(applicationContext, "Empty section? Come on...", Toast.LENGTH_LONG).show()
            } else {
                val resultIntent = Intent()
                val bundle = Bundle()
                bundle.putParcelable(KEY_SECTION, section)
                resultIntent.putExtras(bundle)
                setResult(requestCode, resultIntent)
                supportFinishAfterTransition()
            }
        }
    }

    private fun setTitleText() {
        text_section_title.text = section.title
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val dialog = Dialog(this)
        dialog.setTitle("Edit person")
        dialog.setContentView(R.layout.dialog_edit_person)
        val text = dialog.findViewById<EditText>(R.id.text_listitem_edit_person)
        text.setText(section.studentsNames[position])
        dialog.findViewById<Button>(R.id.button_person_edit_done).setOnClickListener {
            section.studentsNames[position] = text.text.toString()
            adapter.notifyDataSetChanged()
            dialog.dismiss()
        }

        dialog.show()
        dialog.window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
    }

    companion object {

        const val KEY_SECTION = "Section"
        const val KEY_REQUEST_CODE = "RequestCode"
        const val NEW_SECTION = 100
        const val UPDATE_SECTION = 101

        fun makeIntent(context: Context): Intent {
            return Intent(context, SectionSquadActivity::class.java)
        }
    }

}
