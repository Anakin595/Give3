package com.give3.gizrog.give3;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class SectionsActivity extends AppCompatActivity {

    private List<Section> sections = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sections);
        sections.add(new Section("+", null));

        RecyclerView recyclerView = findViewById(R.id.recyclerView_section);
        SectionRecyclerViewAdapter adapter = new SectionRecyclerViewAdapter(this, sections);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(adapter);


        //getWindow().setEnterTransition(null);
    }

    public static Intent makeIntent(Context ctx) {
        return new Intent(ctx, SectionsActivity.class);
    }
}
