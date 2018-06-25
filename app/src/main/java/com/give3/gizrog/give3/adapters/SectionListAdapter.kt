package com.give3.gizrog.give3.adapters

import android.app.Activity
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.*
import com.give3.gizrog.give3.R

class SectionListAdapter(private var activity: Activity, private var items: ArrayList<String>): BaseAdapter() {

    private class ViewHolder(row: View?) {
        var txtName: EditText? = null
        var btnOk: Button? = null
        var btnDel: Button? = null

        init {
            this.txtName = row?.findViewById(R.id.listview_text)
            this.btnOk = row?.findViewById(R.id.add_listitem)
            this.btnDel = row?.findViewById(R.id.delete_listitem)
        }
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View?
        val viewHolder: ViewHolder
        if (convertView == null) {
            val inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.custom_listview_item, null)
            viewHolder = ViewHolder(view)
            view?.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        viewHolder.txtName?.setText(items[position])

        if (position == 0) {
            viewHolder.btnDel?.visibility = View.GONE
        }

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


        viewHolder.btnOk?.setOnClickListener({
            items.add("add...")
            //Toast.makeText(this.activity, "Added", Toast.LENGTH_LONG).show()
            this.notifyDataSetChanged()
        })

        viewHolder.btnDel?.setOnClickListener({
            items.removeAt(position)
            //Toast.makeText(this.activity, "Deleted", Toast.LENGTH_LONG).show()
            this.notifyDataSetChanged()
        })

        return view as View
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

}