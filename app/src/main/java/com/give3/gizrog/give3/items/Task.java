package com.give3.gizrog.give3.items;

import java.util.ArrayList;
import java.util.List;

public class Task {

    String title;

    List<SubTask> subtasks = new ArrayList<>();

    Task() {}

    Task(String title) {
        this.title = title;
    }

    public class SubTask {

        String subTaskTitle;

        SubTask() {}

        SubTask(String subTaskTitle) {
            this.subTaskTitle = subTaskTitle;
        }


    }

}
