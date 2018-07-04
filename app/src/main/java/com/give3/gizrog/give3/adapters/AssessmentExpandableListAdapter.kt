package com.give3.gizrog.give3.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.BaseExpandableListAdapter
import com.give3.gizrog.give3.R
import com.give3.gizrog.give3.models.AssessmentSection
import com.give3.gizrog.give3.models.Task
import com.give3.gizrog.give3.viewholders.AssessmentChildItemViewHolder
import com.give3.gizrog.give3.viewholders.AssessmentParentItemViewHolder


class AssessmentExpandableListAdapter(val context: Context, val parentItemList: ArrayList<AssessmentSection>): BaseExpandableListAdapter() {

    val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getGroup(groupPosition: Int): AssessmentSection {
        return parentItemList[groupPosition]
    }

    override fun isChildSelectable(p0: Int, p1: Int): Boolean {
        return true
    }

    override fun hasStableIds(): Boolean {
        return true
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val parentViewHolder: AssessmentParentItemViewHolder
        if(convertView == null) {
            view = inflater.inflate(R.layout.listitem_parent_assessment, null)
            parentViewHolder = AssessmentParentItemViewHolder(view)
            view.tag = parentViewHolder
        } else {
            parentViewHolder = convertView.tag as AssessmentParentItemViewHolder
            view = convertView
        }
        val parentItem = getGroup(groupPosition)
        parentViewHolder.textTitle.text = parentItem.section.title
        parentViewHolder.textGrade.text = parentItem.calculateGrade().toString()
        val completion = "${parentItem.countTasksCompleted()}/${getChildrenCount(groupPosition)}"
        parentViewHolder.textCompletition.text = completion

        return view
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return parentItemList[groupPosition].tasks.size
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Task {
        return parentItemList[groupPosition].tasks[childPosition]
    }

    override fun getGroupId(p0: Int): Long {
        return 0
    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val childViewHolder: AssessmentChildItemViewHolder
        if(convertView == null) {
            view = inflater.inflate(R.layout.listitem_child_assessment, null)
            childViewHolder = AssessmentChildItemViewHolder(view)
            view.tag = childViewHolder
        } else {
            childViewHolder = convertView.tag as AssessmentChildItemViewHolder
            view = convertView
        }
        val childItem = getChild(groupPosition, childPosition)
        childViewHolder.textTask.text = childItem.title
        childViewHolder.checkBox.isChecked = childItem.isComplete

        return view
    }

    override fun getChildId(p0: Int, p1: Int): Long {
        return 0
    }

    override fun getGroupCount(): Int {
        return parentItemList.size
    }

}