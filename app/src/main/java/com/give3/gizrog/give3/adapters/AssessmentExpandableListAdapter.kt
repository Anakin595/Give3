package com.give3.gizrog.give3.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.view.LayoutInflater
import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter
import com.give3.gizrog.give3.R
import com.give3.gizrog.give3.models.AssessmentSection
import com.give3.gizrog.give3.models.Task
import com.give3.gizrog.give3.viewholders.AssessmentChildItemViewHolder
import com.give3.gizrog.give3.viewholders.AssessmentParentItemViewHolder


class AssessmentExpandableListAdapter(val context: Context, val parentItemList: List<*>) : ExpandableRecyclerAdapter<AssessmentParentItemViewHolder, AssessmentChildItemViewHolder>  {

    private var mInflater: LayoutInflater = LayoutInflater.from(context)
    private val sections: ArrayList<String> = ArrayList()
    private var tasks: ArrayList<Task> = ArrayList()

    override fun onCreateParentViewHolder(viewGroup: ViewGroup): AssessmentParentItemViewHolder {
        val view = mInflater.inflate(R.layout.listitem_header_assessment, viewGroup, false)
        return AssessmentParentItemViewHolder(view)
    }

    override fun onCreateChildViewHolder(viewGroup: ViewGroup): AssessmentChildItemViewHolder {
        val view = mInflater.inflate(R.layout.listitem_child_assessment, viewGroup, false)
        return AssessmentChildItemViewHolder(view)
    }

    override fun onBindParentViewHolder(parentViewHolder: AssessmentParentItemViewHolder, i: Int, parentObject: Any) {
        val section = parentObject as AssessmentSection
        sections.add(section.title)
        parentViewHolder.textTitle.text = sections[i]
        parentViewHolder.textGrade.text = section.grade.toString()
        val completition = "${section.countTasksCompleted()}/${section.tasks.size}"
        parentViewHolder.textCompletition.text = "0/0"
    }

    override fun onBindChildViewHolder(childViewHolder: AssessmentChildItemViewHolder, i: Int, childObject: Any) {
        val childItem = childObject as ArrayList<*>
        val title = "Section $i"
        childViewHolder.mChildTextView.setText(title)
        if (i == 1) {
            childViewHolder.mChildDeleteButton.setVisibility(View.GONE)
        }

        childViewHolder.mChildAddButton.setOnClickListener(View.OnClickListener {
            Toast.makeText(mContext, "TOASTT", Toast.LENGTH_SHORT).show()
            addItem(i)
        })
    }

    private fun addItem(i: Int) {

    }


}