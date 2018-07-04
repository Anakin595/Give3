package com.give3.gizrog.give3.viewholders

import android.view.View
import android.widget.TextView
import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder
import com.give3.gizrog.give3.R

class AssessmentParentItemViewHolder(itemView: View): ParentViewHolder(itemView) {

    val textTitle: TextView = itemView.findViewById(R.id.text_listitem_assessment_header)
    val textGrade: TextView = itemView.findViewById(R.id.text_listitem_assessment_header_grade)
    val textCompletition: TextView = itemView.findViewById(R.id.text_listitem_assessment_header_completition)

}