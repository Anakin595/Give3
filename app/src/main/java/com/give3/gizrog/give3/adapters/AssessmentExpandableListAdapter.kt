package com.give3.gizrog.give3.adapters

import android.app.Dialog
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.BaseExpandableListAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.give3.gizrog.give3.R
import com.give3.gizrog.give3.models.AssessmentSection
import com.give3.gizrog.give3.models.Task
import com.give3.gizrog.give3.viewholders.AssessmentChildItemViewHolder
import com.give3.gizrog.give3.viewholders.AssessmentParentItemViewHolder


class AssessmentExpandableListAdapter(val context: Context, val parentItemList: ArrayList<AssessmentSection>): BaseExpandableListAdapter() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

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
        val grade = "%.1f".format(parentItem.grade)
        parentViewHolder.textGrade.text = grade
        val completion = "${parentItem.countTasksCompleted()}/${getChildrenCount(groupPosition)}"
        parentViewHolder.textCompletition.text = completion

        parentViewHolder.textGrade.setOnClickListener {
            showEditGradeDialog(groupPosition)
        }

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

    private fun showEditGradeDialog(groupPosition: Int) {
        val dialog = Dialog(context)
        dialog.setTitle("Edit grade")
        dialog.setContentView(R.layout.dialog_edit_grade)
        val text = dialog.findViewById<EditText>(R.id.text_listitem_edit_grade)
        text.setText(parentItemList[groupPosition].grade.toString())
        val button = dialog.findViewById<Button>(R.id.button_assessment_edit_done)

        text.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s != null &&  s.isNotEmpty()) {
                    val value = s.toString().toFloat()
                    if(value > 5 || value < 2) {
                        Toast.makeText(context, "Insert value between 2.0 and 5.0", Toast.LENGTH_SHORT).show()
                        button.isEnabled = false
                    } else {
                        button.isEnabled = true
                    }
                } else button.isEnabled = false

            }
            override fun afterTextChanged(s: Editable?) {}
        })

        button.setOnClickListener {
            parentItemList[groupPosition].grade = text.text.toString().toFloat()
            this.notifyDataSetChanged()
            dialog.dismiss()
        }

        dialog.show()
        dialog.window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT)
    }

    override fun getChildId(p0: Int, p1: Int): Long {
        return 0
    }

    override fun getGroupCount(): Int {
        return parentItemList.size
    }

}