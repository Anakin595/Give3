package com.give3.gizrog.give3.items

import java.util.ArrayList

class Task {

    internal var title: String = ""

    internal var subtasks: List<SubTask> = ArrayList()

    internal constructor() {}

    internal constructor(title: String) {
        this.title = title
    }

    inner class SubTask {

        internal var subTaskTitle: String = ""

        internal constructor() {}

        internal constructor(subTaskTitle: String) {
            this.subTaskTitle = subTaskTitle
        }


    }

}
