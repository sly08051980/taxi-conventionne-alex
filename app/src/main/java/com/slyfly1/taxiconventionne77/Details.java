package com.slyfly1.taxiconventionne77;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Details extends AppCompatActivity {
TextView textTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        textTitle =findViewById(R.id.detailTitle);

        Intent i= getIntent();
        String title = i.getStringExtra("title");
        textTitle.setText(title);

    }
}
