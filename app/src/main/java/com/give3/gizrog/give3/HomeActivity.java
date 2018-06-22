package com.give3.gizrog.give3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setupStartButton();
    }

    private void setupStartButton() {
        Button btn = findViewById(R.id.startButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sectionsIntent = MenuActivity.makeIntent(HomeActivity.this);
                startActivity(sectionsIntent);
            }
        });
    }
}
