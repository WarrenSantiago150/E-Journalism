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

public class sportspage extends AppCompatActivity {

    Button Cancel, Submit;
    EditText sports;
    String usernem;

    FirebaseDatabase UsersDatabase;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sportspage);

        Cancel = (Button) findViewById(R.id.Sports_cancel);

        String use = getIntent().getStringExtra("UserName");
        usernem = use;

        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(sportspage.this, Categories.class);
                startActivity(intent);

            }
        });

        Submit = (Button) findViewById(R.id.Sports_submit);
        sports =  findViewById(R.id.Sports);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UsersDatabase = FirebaseDatabase.getInstance();
                reference = UsersDatabase.getReference("SportsEntry");

                String entry= sports.getText().toString();
                String user = usernem;

                sports_helper sports_helper = new sports_helper(entry,user);

                Toast.makeText(sportspage.this,"You have submitted your entry.",Toast.LENGTH_SHORT).show();

                reference.child(usernem).setValue(sports_helper);
            }
        });


    }
}