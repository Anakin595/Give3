package com.give3.gizrog.give3;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

public class SectionsActivity extends AppCompatActivity {

    private static final String TAG = "SectionsActivity";

    private ArrayList<String> data = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sections);

        Log.d(TAG, "onCreate: started.");

        initRecyclerView();
    }

    private void initRecyclerView() {
        data.add("create section...");

        Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recycler = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this.data, this);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));
    }


    public static Intent makeIntent(Context ctx) {
        return new Intent(ctx, SectionsActivity.class);
    }

}
