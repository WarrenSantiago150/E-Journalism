package com.example.ejournalism;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class display_entry extends AppCompatActivity {
    TextView entry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_entry);

        entry= findViewById(R.id.EntryView);
        String user = getIntent().getStringExtra("entry");

        entry.setText(user);

        TextView textview= (TextView) findViewById(R.id.EntryView);
        textview.setMovementMethod(new ScrollingMovementMethod());


    }
}