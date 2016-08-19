package com.example.abhi.newspeak;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class HeadlinesDisplayActivity extends AppCompatActivity {

    RecyclerView headlinesRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_headlines_display);

        headlinesRecyclerView = (RecyclerView) findViewById(R.id.headlinesRecyclerView);

        RecyclerAdapter adapter = new RecyclerAdapter(this);
        headlinesRecyclerView.setAdapter(adapter);
        headlinesRecyclerView.setHasFixedSize(true);

        //layouut manager for recylerView
        headlinesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
