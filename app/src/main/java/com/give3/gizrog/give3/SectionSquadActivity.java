package com.give3.gizrog.give3;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SectionSquadActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    Bundle controlBundle;

    List<String> studentsNames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_squad);

        studentsNames.add("add new student");

        Intent intent = getIntent();
        String title = Objects.requireNonNull(intent.getExtras()).getString("Title");
        studentsNames = intent.getExtras().getStringArrayList("Students");

        ListView listView = findViewById(R.id.listview_section_squad);
        CustomViewAdapter customViewAdapter = new CustomViewAdapter();
        listView.setAdapter(customViewAdapter);
    }

    @Nullable
    @Override
    public Intent getParentActivityIntent() {
        return super.getParentActivityIntent();
    }


    public static Intent makeIntent(Context context, Bundle bundle) {
        return new Intent(context, SectionSquadActivity.class);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(this, "ADDED", Toast.LENGTH_SHORT).show();
        studentsNames.add("new student");
    }

    class CustomViewAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return studentsNames.size();
        }

        @Override
        public Object getItem(int i) {
            return studentsNames.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @SuppressLint("ViewHolder")
        @Override
        public View getView(int i, View view, final ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.custom_listview_item, null);
            EditText editText = view.findViewById(R.id.listview_text);
            editText.setText(studentsNames.get(i));

            Button btn = view.findViewById(R.id.add_listitem);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    studentsNames.add("new student...");
                    viewGroup.refreshDrawableState();
                }
            });
            return view;
        }
    }

}
