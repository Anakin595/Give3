package com.give3.gizrog.give3.viewholders

import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder
import com.give3.gizrog.give3.R

class AssessmentChildItemViewHolder(itemView: View): ChildViewHolder(itemView) {

    val textTask: TextView = itemView.findViewById(R.id.text_listitem_child_assessment)
    val textCheckBox: CheckBox = itemView.findViewById(R.id.checkbox_listitem_child_assessment)

}