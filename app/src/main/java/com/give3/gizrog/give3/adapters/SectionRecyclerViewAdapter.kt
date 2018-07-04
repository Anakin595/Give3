package com.give3.gizrog.give3.adapters

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.give3.gizrog.give3.R
import com.give3.gizrog.give3.models.Section
import com.give3.gizrog.give3.listeners.RecyclerViewClickListener

class SectionRecyclerViewAdapter(private val mContext: Context,
                                 private val itemListener: RecyclerViewClickListener,
                                 private val sections: List<Section>) : RecyclerView.Adapter<SectionRecyclerViewAdapter.SectionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionViewHolder {
        val mInflater = LayoutInflater.from(mContext)
        val view = mInflater.inflate(R.layout.cardview_item_section, parent, false)
        return SectionViewHolder(view)
    }

    override fun onBindViewHolder(holder: SectionViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder: called")
        holder.sectionTitle.text = sections[position].title
        holder.cardView.elevation = sections[position].focus
    }

    override fun getItemCount(): Int {
        return sections.size
    }

    fun resetFocus() {
        sections.forEach {
            it.focus = 3f
        }
    }

    inner class SectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {


        internal var sectionTitle: TextView = itemView.findViewById(R.id.text_cardView_section)
        internal var cardView: CardView = itemView.findViewById(R.id.cardView_section)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            itemListener.recyclerViewListClicked(v, this.layoutPosition)
        }
    }

    companion object {
        private val TAG = "SecRecViewAdapter"
    }
}
