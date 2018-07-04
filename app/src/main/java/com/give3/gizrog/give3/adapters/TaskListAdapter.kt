package com.give3.gizrog.give3.adapters

import android.app.Activity
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.give3.gizrog.give3.R
import com.give3.gizrog.give3.models.Task

class TaskListAdapter(private var activity: Activity, private var items: ArrayList<Task>): BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val taskViewHolder: TaskViewHolder
        if (convertView == null) {
            val inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.layout_listitem_task, null)
            taskViewHolder = TaskViewHolder(view)
            view?.tag = taskViewHolder
        } else {
            taskViewHolder = convertView.tag as TaskViewHolder
            view = convertView
        }

        taskViewHolder.ref = position
        taskViewHolder.txtName?.text = items[position].title
        taskViewHolder.txtWeight?.text = items[position].weight.toString()

        taskViewHolder.btnDel?.setOnClickListener {
            items.removeAt(taskViewHolder.ref)
            this.notifyDataSetChanged()
        }

        return view
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
           return position.toLong()
    }

    override fun getCount(): Int {
        return items.size
    }

    private class TaskViewHolder(row: View?) {
        var txtName: TextView? = row?.findViewById(R.id.text_listitem_task)
        var txtWeight: TextView? = row?.findViewById(R.id.text_listitem_task_weight)
        var btnDel: Button? = row?.findViewById(R.id.button_listitem_task_delete)
        var ref: Int = 0
    }

}