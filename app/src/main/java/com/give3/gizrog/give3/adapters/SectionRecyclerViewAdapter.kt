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
import com.give3.gizrog.give3.Section
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
        Log.d(TAG, "position = " + position)
    }

    override fun getItemCount(): Int {
        return sections.size
    }

    inner class SectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {


        internal var sectionTitle: TextView
        internal var cardView: CardView

        init {
            this.sectionTitle = itemView.findViewById(R.id.item_text_section)
            this.cardView = itemView.findViewById(R.id.item_cardView_section)
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            itemListener.recyclerViewListClicked(v, this.position)
            this.sectionTitle.text = if (this.sectionTitle.text == "+") "+" else this.position.toString()
        }
    }

    companion object {

        private const val NEW_ITEM = "+"
        private val TAG = "SecRecViewAdapter"
    }
}
