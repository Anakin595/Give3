package com.give3.gizrog.give3;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;

import java.util.List;

public class Section implements ParentObject {

    String title;

    private List<Object> mChildrenList;

    Section(String title) {
        this.title = title;
    }

    @Override
    public List<Object> getChildObjectList() {
        return mChildrenList;
    }

    @Override
    public void setChildObjectList(List<Object> list) {
        mChildrenList = list;
    }

}
