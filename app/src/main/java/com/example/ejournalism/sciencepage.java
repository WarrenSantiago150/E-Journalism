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

public class sciencepage extends AppCompatActivity {

    Button Cancel,Submit;
    EditText science;
    String usernem;

    FirebaseDatabase UsersDatabase;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sciencepage);

        Cancel = (Button) findViewById(R.id.Science_cancel);

        String use = getIntent().getStringExtra("UserName");
        usernem = use;

        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(sciencepage.this, Categories.class);
                startActivity(intent);

            }
        });

        Submit = (Button) findViewById(R.id.Science_submit);
        science =  findViewById(R.id.Science);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UsersDatabase = FirebaseDatabase.getInstance();
                reference = UsersDatabase.getReference("ScienceEntry");

                String entry= science.getText().toString();
                String user = usernem;

                science_helper science_helper = new science_helper(entry,user);

                Toast.makeText(sciencepage.this,"You have submitted your entry.",Toast.LENGTH_SHORT).show();

                reference.child(usernem).setValue(science_helper);
            }
        });

    }
}