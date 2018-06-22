package com.give3.gizrog.give3;

import java.util.List;

public class Section {

    String title;

    private List<String> studentsNames;

    public Section(String title, List<String> studentsNames) {
        this.title = title;
        this.studentsNames = studentsNames;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getStudentsNames() {
        return studentsNames;
    }

    public void setStudentsNames(List<String> studentsNames) {
        this.studentsNames = studentsNames;
    }
}
