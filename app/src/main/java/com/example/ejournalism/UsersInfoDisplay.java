package com.example.ejournalism;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UsersInfoDisplay extends AppCompatActivity {

    TextView emil, usernim, passward,taip;
    Button Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_info_display);

        Back = (Button) findViewById(R.id.back);

        emil = findViewById(R.id.Email);
        usernim = findViewById(R.id.Name);
        passward = findViewById(R.id.pass);
        taip = findViewById(R.id.type);

        String Email = getIntent().getStringExtra("Email");
        String Type = getIntent().getStringExtra("Type");
        String Password = getIntent().getStringExtra("Password");
        String username = getIntent().getStringExtra("Name");


        emil.setText(Email);
        usernim.setText(username);
        passward.setText(Password);
        taip.setText(Type);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UsersInfoDisplay.this, Admin.class);
                startActivity(intent);

            }
        });

    }
}