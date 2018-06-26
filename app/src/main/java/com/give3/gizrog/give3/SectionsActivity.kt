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
            sections.add(Section("+", arrayListOf("add...")))
        }

        initialize()
    }

    private fun initialize() {
        initializeSaveButton()
        setRecyclerViewAdapter()
        window.enterTransition = null
    }

    private fun initializeSaveButton() {
        findViewById<Button>(R.id.button_sections_save).setOnClickListener {
            setResult(intent.getIntExtra("RequestCode",1))
            finish()
        }
    }

    private fun setRecyclerViewAdapter() {
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView_section)
        recyclerViewAdapter = SectionRecyclerViewAdapter(this, this, sections)
        recyclerView.layoutManager = GridLayoutManager(this, 3)
        recyclerView.adapter = recyclerViewAdapter
    }

    override fun recyclerViewListClicked(v: View, position: Int) {
        val intent = SectionSquadActivity.makeIntent(this, Bundle())
        val bundle = Bundle()
        bundle.putParcelable("Section", sections[position])
        bundle.putString("SectionId",position.toString())
        intent.putExtras(bundle)
        val options =
                ActivityOptionsCompat.makeSceneTransitionAnimation(
                        this,
                        v.findViewById<CardView>(R.id.item_cardView_section),   // Starting view
                        getString(R.string.transition_card)
                )
        startActivityForResult(this, intent, (position == sections.lastIndex).int, options.toBundle())
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val section: Section? = data?.getParcelableExtra("Section")
        val sectionId = data?.getIntExtra("SectionId",0)
        if (data != null) {
            when (resultCode) {
                RESULT_NEW_SECTION -> sections.add(sectionId!!, section!!)
                RESULT_UPDATE_SECTION -> sections[sectionId!!].studentsNames = section!!.studentsNames
            }
        }
        recyclerViewAdapter?.notifyDataSetChanged()
    }

    companion object {


        const val RESULT_NEW_SECTION: Int = SectionSquadActivity.RESULT_NEW_SECTION
        const val RESULT_UPDATE_SECTION: Int = SectionSquadActivity.RESULT_UPDATE_SECTION

        fun makeIntent(ctx: Context): Intent {
            return Intent(ctx, SectionsActivity::class.java)
        }

        val Boolean.int
            get() = if (this) 1 else 0
    }
}
