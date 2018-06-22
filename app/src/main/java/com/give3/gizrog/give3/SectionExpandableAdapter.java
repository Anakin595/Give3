package com.give3.gizrog.give3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.give3.gizrog.give3.viewholders.BaseParentViewHolder;
import com.give3.gizrog.give3.viewholders.SectionChildViewHolder;

import java.util.ArrayList;
import java.util.List;

public class SectionExpandableAdapter extends ExpandableRecyclerAdapter<BaseParentViewHolder, SectionChildViewHolder> {

    private LayoutInflater mInflater;
    private List<Section> sections = new ArrayList<>();

    public SectionExpandableAdapter(Context context, List parentItemList) {
        super(context, parentItemList);

        mInflater = LayoutInflater.from(context);
    }

    @Override
    public BaseParentViewHolder onCreateParentViewHolder(ViewGroup viewGroup) {
        View view = mInflater.inflate(R.layout.layout_parent_listitem, viewGroup, false);
        return new BaseParentViewHolder(view);
    }

    @Override
    public SectionChildViewHolder onCreateChildViewHolder(ViewGroup viewGroup) {
        View view = mInflater.inflate(R.layout.layout_child_listitem, viewGroup, false);
        return new SectionChildViewHolder(view);
    }

    @Override
    public void onBindParentViewHolder(BaseParentViewHolder baseParentViewHolder, int i, Object parentObject) {
        sections.add((Section) parentObject);
        baseParentViewHolder.mParentTextView.setText(sections.get(i).title);
    }

    @Override
    public void onBindChildViewHolder(SectionChildViewHolder sectionChildViewHolder, final int i, Object childObject) {
        ChildItem childItem = (ChildItem) childObject;
        String title = "Section " + i;
        sectionChildViewHolder.mChildTextView.setText(title);
        if(i == 1) {
            sectionChildViewHolder.mChildDeleteButton.setVisibility(View.GONE);
        }

        sectionChildViewHolder.mChildAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "TOASTT", Toast.LENGTH_SHORT).show();
                addItem(i);
            }
        });
    }

    private void addItem(int i) {

    }


}

