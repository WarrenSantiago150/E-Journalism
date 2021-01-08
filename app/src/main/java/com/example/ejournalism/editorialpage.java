package com.example.ejournalism;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class editorialpage extends AppCompatActivity {

    Button Cancel,Editorial_Entry;
    EditText Editorial;
    String usernem;

    FirebaseDatabase UsersDatabase;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editorialpage);

        Cancel = (Button) findViewById(R.id.Editorial_cancel);
        String use = getIntent().getStringExtra("UserName");
        usernem = use;

        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(editorialpage.this, Categories.class);
                startActivity(intent);

            }
        });

        Editorial_Entry = (Button) findViewById(R.id.Editorial_submit);
        Editorial =  findViewById(R.id.Editorial);

        Editorial_Entry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UsersDatabase = FirebaseDatabase.getInstance();
                reference = UsersDatabase.getReference("EditorialEntry");

                String entry= Editorial.getText().toString();
                String user = usernem;

                editorial_helper editorial_helper = new editorial_helper(entry, user);

                reference.child(usernem).setValue(editorial_helper);
                Toast.makeText(editorialpage.this,"You have submitted your entry.",Toast.LENGTH_SHORT).show();

            }
        });


    }
}