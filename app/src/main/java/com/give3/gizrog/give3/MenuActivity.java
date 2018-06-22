package com.give3.gizrog.give3;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.give3.gizrog.give3.items.Task;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    private static final String TAG = "MenuActivity";

    private ArrayList<ParentObject> objectList = new ArrayList<>();


    private List<Section> sections = new ArrayList<>();
    private List<Task> tasks = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Log.d(TAG, "onCreate: started.");

        initializeSectionsButton();
        initializeTasksButton();



    }


    private void initializeSectionsButton() {
        TextView view = findViewById(R.id.textView_section);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sectionsIntent = SectionsActivity.makeIntent(MenuActivity.this);

                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        MenuActivity.this,
                        findViewById(R.id.textView_section),
                        "fromMenu");
                //getWindow().setEnterTransition(null);

                startActivity(sectionsIntent, optionsCompat.toBundle());
            }
        });
    }

    private void initializeTasksButton() {
        TextView view = findViewById(R.id.textView_tasks);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }



    public static Intent makeIntent(Context ctx) {
        return new Intent(ctx, MenuActivity.class);
    }
}
