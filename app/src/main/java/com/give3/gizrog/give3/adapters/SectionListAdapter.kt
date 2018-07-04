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

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val sectionViewHolder: SectionViewHolder
        if (convertView == null) {
            val inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.layout_listitem_section_person, null)
            sectionViewHolder = SectionViewHolder(view)
            view?.tag = sectionViewHolder
        } else {
            sectionViewHolder = convertView.tag as SectionViewHolder
            view = convertView
        }

        sectionViewHolder.ref = position
        sectionViewHolder.txtName?.setText(items[position])

        sectionViewHolder.txtName?.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                items[sectionViewHolder.ref] = s.toString()
            }
        })

        sectionViewHolder.btnDel?.setOnClickListener {
            items.removeAt(sectionViewHolder.ref)
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

    private class SectionViewHolder(row: View?) {
        var txtName: EditText? = row?.findViewById(R.id.text_listitem_section_person)
        var btnDel: Button? = row?.findViewById(R.id.button_listitem_section_person_del)
        var ref: Int = 0
    }

}