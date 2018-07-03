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

class SectionListAdapter(private var activity: Activity, private var items: ArrayList<String>): BaseAdapter() {

    private class ViewHolder(row: View?) {
        var txtName: EditText? = row?.findViewById(R.id.text_listitem_section_person)
        var btnDel: Button? = row?.findViewById(R.id.button_listitem_section_person_del)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View?
        val viewHolder: ViewHolder
        if (convertView == null) {
            val inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.layout_listview_section_person_item, null)
            viewHolder = ViewHolder(view)
            view?.tag = viewHolder

        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        viewHolder.txtName?.setText(items[position])

        viewHolder.txtName?.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                items[position] = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
                items[position] = s.toString()
            }
        })

        viewHolder.btnDel?.setOnClickListener {
            items.removeAt(position)
            this.notifyDataSetChanged()
        }

        return view as View
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    fun add(item: String) {
        items.add(item)
        this.notifyDataSetChanged()
    }

    override fun getItemId(position: Int): Long {
           return position.toLong()
    }

    override fun getCount(): Int {
        return items.size
    }

}