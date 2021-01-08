package com.example.ejournalism;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class photopage extends AppCompatActivity {

    Button Cancel,Submit;
    EditText photo;
    String usernem;

    FirebaseDatabase UsersDatabase;
    DatabaseReference reference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photopage);

        Cancel = (Button) findViewById(R.id.Photo_cancel);

        String use = getIntent().getStringExtra("UserName");
        usernem = use;

        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(photopage.this, Categories.class);
                startActivity(intent);

            }
        });

        Submit = (Button) findViewById(R.id.Photo_submit);
        photo =  findViewById(R.id.Photo);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UsersDatabase = FirebaseDatabase.getInstance();
                reference = UsersDatabase.getReference("PhotoEntry");

                String entry= photo.getText().toString();
                String user = usernem;

                photo_helper photo_helper = new photo_helper(entry,user);

                Toast.makeText(photopage.this,"You have submitted your entry.",Toast.LENGTH_SHORT).show();

                reference.child(usernem).setValue(photo_helper);
            }
        });


    }
}