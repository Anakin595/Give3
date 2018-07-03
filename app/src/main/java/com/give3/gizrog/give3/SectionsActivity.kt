package com.give3.gizrog.give3

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.widget.CardView
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import com.give3.gizrog.give3.adapters.SectionRecyclerViewAdapter
import com.give3.gizrog.give3.models.Section
import com.give3.gizrog.give3.listeners.RecyclerViewClickListener

import kotlin.collections.ArrayList

class SectionsActivity : BaseAppCompactActivity(), RecyclerViewClickListener {

    private var sections: ArrayList<Section> = ArrayList()
    private var recyclerViewAdapter: SectionRecyclerViewAdapter? = null
    private var focusedSectionId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sections)
        sections = intent.getParcelableArrayListExtra("Sections")
        initialize()
    }

    private fun initialize() {
        initializeSaveButton()
        initializeAddButton()
        initializeEditButton()
        initializeDeleteButton()
        setRecyclerViewAdapter()
        window.enterTransition = null
    }

    private fun initializeSaveButton() {
        findViewById<Button>(R.id.button_section_done).setOnClickListener {
                val resultIntent = Intent()
                val bundle = Bundle()
                bundle.putParcelableArrayList(KEY_SECTIONS, sections)
                resultIntent.putExtras(bundle)
                setResult(RESULT_SECTION, resultIntent)
                supportFinishAfterTransition()
        }
    }

    private fun initializeAddButton() {
        findViewById<Button>(R.id.button_section_add).setOnClickListener {
            val intent = SectionSquadActivity.makeIntent(this)
            val bundle = Bundle()
            bundle.putParcelable(KEY_SECTION, Section(sections.lastIndex+1, "New", ArrayList()))
            bundle.putInt(KEY_REQUEST_CODE, NEW_SECTION)
            intent.putExtras(bundle)
            val options =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(
                            this,
                            findViewById(R.id.recyclerView_section),   // Starting view
                            getString(R.string.transition_card)
                    )
            startActivityForResult(this, intent, 1, options.toBundle())
        }
    }

    private fun initializeEditButton() {
        findViewById<Button>(R.id.button_section_edit).setOnClickListener {
            val resultIntent = Intent()
            val bundle = Bundle()
            bundle.putInt(KEY_REQUEST_CODE, UPDATE_SECTION)
            bundle.putParcelable(KEY_SECTIONS, sections[focusedSectionId!!])
            resultIntent.putExtras(bundle)
            setResult(RESULT_SECTION, resultIntent)
            supportFinishAfterTransition()
        }
    }
    private fun initializeDeleteButton() {
        findViewById<Button>(R.id.button_section_delete).setOnClickListener {
            sections.removeAt(focusedSectionId!!)
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
        val cardView = v.findViewById<CardView>(R.id.cardView_section)
        val buttonsNormal = findViewById<ConstraintLayout>(R.id.clayout_bottom_buttons_normal)
        val buttonsFocused = findViewById<ConstraintLayout>(R.id.clayout_bottom_buttons_focus)
        if (focusedSectionId == null) {
            focusedSectionId = position
            cardView.elevation = 60f
            buttonsNormal.visibility = View.GONE
            buttonsFocused.visibility = View.VISIBLE
        } else {
            focusedSectionId = null
            cardView.elevation = 15f
            buttonsNormal.visibility = View.VISIBLE
            buttonsFocused.visibility = View.GONE
        }
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
        const val KEY_REQUEST_CODE: String = SectionSquadActivity.KEY_REQUEST_CODE
        const val KEY_SECTION: String = SectionSquadActivity.KEY_SECTION
        const val NEW_SECTION: Int = SectionSquadActivity.NEW_SECTION
        const val UPDATE_SECTION: Int = SectionSquadActivity.UPDATE_SECTION
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
