package com.give3.gizrog.give3;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
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
    public void onBindViewHolder(@NonNull final SectionViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called");

        if(position == sections.size()-1) {
            holder.sectionTitle.setText("+");
        } else {
            holder.sectionTitle.setText(sections.get(position).title);
        }


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = SectionSquadActivity.makeIntent(mContext,null);
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("Students", (ArrayList<String>) sections.get(holder.getAdapterPosition()).getStudentsNames());
                bundle.putString("Title", sections.get(holder.getAdapterPosition()).getTitle());
                intent.putExtras(bundle);
                mContext.startActivity(intent);

                // Ordinary Intent for launching a new activity

                // Get the transition name from the string
//                String transitionName = mContext.getString(R.string.transition_string);
//
//                // Define the view that the animation will start from
//                View viewStart = holder.itemView.findViewById(R.id.item_cardView_section);
//
//                ActivityOptionsCompat options =
//                        ActivityOptionsCompat.makeSceneTransitionAnimation(holder.itemView,
//                                viewStart,   // Starting view
//                                transitionName    // The String
//                        );
//                //Start the Intent
//                ActivityCompat.startActivity(this, intent, options.toBundle());

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
            this.cardView = itemView.findViewById(R.id.item_cardView_section);
        }
    }
}
