package com.give3.gizrog.give3.viewholders

import android.view.View
import android.widget.Button
import android.widget.TextView

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder
import com.give3.gizrog.give3.R

class SectionChildViewHolder(itemView: View) : ChildViewHolder(itemView) {

    var mChildTextView: TextView
    var mChildAddButton: Button
    var mChildDeleteButton: Button

    init {

        this.mChildTextView = itemView.findViewById(R.id.child_text_listitem)
        this.mChildAddButton = itemView.findViewById(R.id.child_add_listitem)
        this.mChildDeleteButton = itemView.findViewById(R.id.child_del_listitem)
    }
}
