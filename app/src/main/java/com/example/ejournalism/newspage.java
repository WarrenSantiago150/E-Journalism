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

public class newspage extends AppCompatActivity {

    Button Cancel,Submit;
    EditText news;
    String usernem;

    FirebaseDatabase UsersDatabase;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newspage);

        Cancel = (Button) findViewById(R.id.News_cancel);

        String use = getIntent().getStringExtra("UserName");
        usernem = use;

        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(newspage.this, Categories.class);
                startActivity(intent);

            }
        });

        Submit = (Button) findViewById(R.id.News_submit);
        news =  findViewById(R.id.News);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UsersDatabase = FirebaseDatabase.getInstance();
                reference = UsersDatabase.getReference("NewsEntry");

                String entry= news.getText().toString();
                String user = usernem;

                news_helper news_helper = new news_helper(entry,user);

                Toast.makeText(newspage.this,"You have submitted your entry.",Toast.LENGTH_SHORT).show();

                reference.child(usernem).setValue(news_helper);
            }
        });


    }
}