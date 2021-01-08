package com.example.ejournalism;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.Feature;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class featurepage extends AppCompatActivity {

    Button Cancel,Submit;
    EditText feature;
    String usernem;

    FirebaseDatabase UsersDatabase;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_featurepage);

        Cancel = (Button) findViewById(R.id.Feature_cancel);

        String use = getIntent().getStringExtra("UserName");
        usernem = use;

        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(featurepage.this, Categories.class);
                startActivity(intent);

            }
        });

        Submit = (Button) findViewById(R.id.Feature_submit);
        feature =  findViewById(R.id.Feature);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UsersDatabase = FirebaseDatabase.getInstance();
                reference = UsersDatabase.getReference("FeatureEntry");

                String entry= feature.getText().toString();
                String user = usernem;

                feature_helper feature_helper = new feature_helper(entry,user);

                Toast.makeText(featurepage.this,"You have submitted your entry.",Toast.LENGTH_SHORT).show();

                reference.child(usernem).setValue(feature_helper);

            }
        });


    }
}