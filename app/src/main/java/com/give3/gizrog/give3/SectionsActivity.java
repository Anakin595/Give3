package com.give3.gizrog.give3;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SectionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sections);

        //getWindow().setEnterTransition(null);
    }

    public static Intent makeIntent(Context ctx) {
        return new Intent(ctx, SectionsActivity.class);
    }
}
