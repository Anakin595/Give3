package com.give3.gizrog.give3.viewholders;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;
import com.give3.gizrog.give3.R;

public class BaseParentViewHolder extends ParentViewHolder {

    public TextView mParentTextView;
    public ImageButton mParentDropdownArrow;

    public BaseParentViewHolder(View itemView) {
        super(itemView);
        mParentTextView = itemView.findViewById(R.id.parent_text_listitem);
        mParentDropdownArrow = itemView.findViewById(R.id.parent_dropdown_arrow);
    }
}
