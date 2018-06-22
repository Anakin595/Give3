package com.give3.gizrog.give3;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class SectionRecyclerViewAdapter extends RecyclerView.Adapter<SectionRecyclerViewAdapter.SectionViewHolder>{

    private static final String TAG = "SecRecViewAdapter";

    private Context mContext;
    private List<Section> sections;

    public SectionRecyclerViewAdapter(Context mContext, List<Section> sections) {
        this.mContext = mContext;
        this.sections = sections;
    }

    @Override
    public SectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        View view = mInflater.inflate(R.layout.cardview_item_section, parent, false);
        return new SectionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SectionViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called");

        if(position == sections.size()-1) {
            holder.sectionTitle.setText("+");

        }
        holder.sectionTitle.setText(sections.get(position).title);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(mContext);
                dialog.setContentView(R.layout.activity_alert_section);
                dialog.setTitle("Create new section.");
                Context ctx = dialog.getContext();
                dialog.show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return sections.size();
    }

    public class SectionViewHolder extends RecyclerView.ViewHolder {

        TextView sectionTitle;
        CardView cardView;

        public SectionViewHolder(View itemView) {
            super(itemView);
            sectionTitle = itemView.findViewById(R.id.item_text_section);
            this.cardView = (CardView) itemView.findViewById(R.id.item_cardView_section);
        }
    }
}
