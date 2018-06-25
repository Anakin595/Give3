package com.give3.gizrog.give3

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.widget.CardView
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.give3.gizrog.give3.adapters.SectionRecyclerViewAdapter
import com.give3.gizrog.give3.listeners.RecyclerViewClickListener

import java.util.ArrayList

class SectionsActivity : AppCompatActivity(), RecyclerViewClickListener {

    private val sections = ArrayList<Section>()
    private var recyclerViewAdapter: SectionRecyclerViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sections)
        val students = ArrayList<String>()
        students.add("add...")
        sections.add(Section("+", students))

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView_section)
        recyclerViewAdapter = SectionRecyclerViewAdapter(this, this, sections)
        //listener
        recyclerView.layoutManager = GridLayoutManager(this, 3)
        recyclerView.adapter = recyclerViewAdapter



        window.enterTransition = null
    }

    override fun recyclerViewListClicked(v: View, position: Int) {
        val intent = SectionSquadActivity.makeIntent(this, Bundle())
        val bundle = Bundle()
        bundle.putStringArrayList("Students", sections[position].studentsNames)
        bundle.putInt("Title", position)
        bundle.putBoolean("last", (sections.size - 1 == position))
        intent.putExtras(bundle)
        val transitionName = getString(R.string.transition_string)
        val viewStart = v.findViewById<CardView>(R.id.item_cardView_section)
        val options =
                ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                        viewStart,   // Starting view
                        transitionName    // The String
                )
        ActivityCompat.startActivityForResult(this, intent,1, options.toBundle())

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == SectionSquadActivity.RESULT_NEW_ITEM) {
            val students = data!!.getStringArrayListExtra(SectionSquadActivity.KEY_STUDENTS)
            val sectionId = data.getIntExtra("Title", 1)
            sections.add(sectionId, Section(title = sectionId.toString(), studentsNames = students))
        } else if(resultCode == SectionSquadActivity.RESULT_UPDATE) {
            val students = data!!.getStringArrayListExtra(SectionSquadActivity.KEY_STUDENTS)
            val sectionId = data.getIntExtra("Title", 1)
            sections[sectionId-1].studentsNames = students
        }
        recyclerViewAdapter?.notifyDataSetChanged()
    }

    companion object {

        fun makeIntent(ctx: Context): Intent {
            return Intent(ctx, SectionsActivity::class.java)
        }
    }
}
