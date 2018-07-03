package com.give3.gizrog.give3

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.widget.CardView
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.give3.gizrog.give3.adapters.SectionRecyclerViewAdapter
import com.give3.gizrog.give3.models.Section
import com.give3.gizrog.give3.listeners.RecyclerViewClickListener

import java.util.ArrayList

class SectionsActivity : BaseAppCompactActivity(), RecyclerViewClickListener {

    private var sections: ArrayList<Section> = ArrayList()
    private var recyclerViewAdapter: SectionRecyclerViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sections)
        sections = intent.getParcelableArrayListExtra("Sections")
        if(sections.isEmpty()){
            sections.add(Section(0,"+", arrayListOf("add...")))
        }
        initialize()
    }

    private fun initialize() {
        initializeSaveButton()
        initializeAddButton()
        setRecyclerViewAdapter()
        window.enterTransition = null
    }

    private fun initializeSaveButton() {
        findViewById<Button>(R.id.button_done).setOnClickListener {
            if(sections.size <= 1) {
                Toast.makeText(applicationContext, "Create at least one section!", Toast.LENGTH_SHORT).show()
            } else {
                val resultIntent = Intent()
                val bundle = Bundle()
                bundle.putParcelableArrayList(KEY_SECTIONS, sections)
                resultIntent.putExtras(bundle)
                setResult(RESULT_SECTION, resultIntent)
                supportFinishAfterTransition()
            }
        }
    }

    private fun initializeAddButton() {
        findViewById<Button>(R.id.button_add).setOnClickListener {
            val resultIntent = Intent()
            val bundle = Bundle()
            bundle.putParcelableArrayList(KEY_SECTIONS, sections)
            resultIntent.putExtras(bundle)
            setResult(RESULT_SECTION, resultIntent)
            supportFinishAfterTransition()
        }
    }

    private fun setRecyclerViewAdapter() {
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView_section)
        recyclerViewAdapter = SectionRecyclerViewAdapter(this, this, sections)
        val layoutManager = GridLayoutManager(this, 3)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = recyclerViewAdapter
    }

    override fun recyclerViewListClicked(v: View, position: Int) {
        val intent = SectionSquadActivity.makeIntent(this)
        val bundle = Bundle()
        bundle.putParcelable(KEY_SECTION, sections[position])
        bundle.putInt("LastIndex", sections.lastIndex)
        intent.putExtras(bundle)
        val options =
                ActivityOptionsCompat.makeSceneTransitionAnimation(
                        this,
                        v.findViewById<CardView>(R.id.item_cardView_section),   // Starting view
                        getString(R.string.transition_card)
                )
        startActivityForResult(this, intent, 1, options.toBundle())
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val section: Section? = data?.getParcelableExtra(KEY_SECTION)
        if (data != null) {
            when (resultCode) {
                RESULT_UPDATE_SECTION -> sections[section!!.id] = section
                RESULT_NEW_SECTION -> sections.add(section!!.id, section)
            }
        }
        recyclerViewAdapter?.notifyDataSetChanged()
    }

    companion object {

        const val KEY_SECTIONS: String = "Sections"
        const val KEY_SECTION: String = SectionSquadActivity.KEY_SECTION
        const val RESULT_NEW_SECTION: Int = SectionSquadActivity.RESULT_NEW_SECTION
        const val RESULT_UPDATE_SECTION: Int = SectionSquadActivity.RESULT_UPDATE_SECTION
        const val RESULT_SECTION: Int = 1

        fun makeIntent(ctx: Context): Intent {
            return Intent(ctx, SectionsActivity::class.java)
        }

        val Boolean.int
            get() = if (this) 1 else 0
    }
}
