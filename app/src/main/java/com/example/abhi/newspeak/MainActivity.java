package com.example.abhi.newspeak;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btnGetHeadlines;
    private TextView displayMessage;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGetHeadlines = (Button) findViewById(R.id.button);
        intent = new Intent(this, HeadlinesDisplayActivity.class);
        btnGetHeadlines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
        displayMessage = (TextView) findViewById(R.id.textView);
    }
}
