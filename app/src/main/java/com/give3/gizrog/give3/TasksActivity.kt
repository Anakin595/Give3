package com.give3.gizrog.give3

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.*
import com.give3.gizrog.give3.adapters.TaskListAdapter
import com.give3.gizrog.give3.models.Task

class TasksActivity : AppCompatActivity(), AdapterView.OnItemClickListener {

    private var tasks: ArrayList<Task> = ArrayList()
    private lateinit var listView: ListView
    private lateinit var adapter: TaskListAdapter
    private var requestCode: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasks)

        tasks = intent.getParcelableArrayListExtra(KEY_TASKS)

        initialize()
    }

    private fun initialize() {
        initializeListView()
        initializeAddButton()
        initializeDoneButton()
        window.enterTransition = null
    }

    private fun initializeListView() {
        listView = findViewById(R.id.listview_tasks)
        adapter = TaskListAdapter(this, tasks)
        listView.onItemClickListener = this
        listView.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun showEditDialog() {

    }

    private fun initializeAddButton() {
        findViewById<Button>(R.id.button_task_add).setOnClickListener {
           tasks.add(Task("new..", 1))
           adapter.notifyDataSetChanged()
        }
    }

    private fun initializeDoneButton() {
        findViewById<Button>(R.id.button_task_done).setOnClickListener {
            if(tasks.isEmpty()) {
                Toast.makeText(applicationContext, "No tasks? Lazy one...", Toast.LENGTH_LONG).show()
            } else {
                val resultIntent = Intent()
                val bundle = Bundle()
                bundle.putParcelableArrayList(KEY_TASKS, tasks)
                resultIntent.putExtras(bundle)
                setResult(requestCode, resultIntent)
                supportFinishAfterTransition()
            }
        }
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val dialog = Dialog(this)
        dialog.setTitle("Edit")
        dialog.setContentView(R.layout.dialog_edit_task)
        val text = dialog.findViewById<EditText>(R.id.text_listitem_edit_task)
        text.setText(tasks[position].title)
        val numberPicker = dialog.findViewById<NumberPicker>(R.id.numberpicker_edit_task)
        numberPicker.minValue = 1
        numberPicker.maxValue = 10
        numberPicker.wrapSelectorWheel = true
        numberPicker.value = tasks[position].weight
        dialog.findViewById<Button>(R.id.button_task_edit_done).setOnClickListener {
            tasks[position].title = text.text.toString()
            tasks[position].weight = numberPicker.value
            adapter.notifyDataSetChanged()
            dialog.dismiss()
        }

        dialog.show()
        dialog.window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT)
    }

    companion object {

        const val RESULT_TASKS: Int = 0
        const val KEY_TASKS: String = "Tasks"

        fun makeIntent(ctx: Context): Intent {
            return Intent(ctx, TasksActivity::class.java)
        }
    }
}
