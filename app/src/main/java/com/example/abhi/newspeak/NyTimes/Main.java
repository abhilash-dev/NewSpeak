package com.example.abhi.newspeak.NyTimes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.abhi.newspeak.R;

public class Main extends AppCompatActivity {

    Button btnShowOutput;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        intent = new Intent(this,FetchData.class);
        btnShowOutput = (Button)findViewById(R.id.btnShowOutput);
        btnShowOutput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }
}
