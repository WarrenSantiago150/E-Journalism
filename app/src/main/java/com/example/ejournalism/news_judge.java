package com.example.ejournalism;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class news_judge extends AppCompatActivity {

    ListView listView;
    DatabaseReference databaseReference;
    List<news_helper> EntryList;
    String EDITU;
    Button NElogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_judge);

        NElogout = (Button) findViewById(R.id.news_logout);
        listView = findViewById(R.id.NewsListView);

        EntryList = new ArrayList<>();
        String user = getIntent().getStringExtra("Username");
        EDITU = user;

        RetrieveList();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                news_helper news_helper = EntryList.get(i);
                String userEntry = news_helper.getEntry();
                Intent intent = new Intent(news_judge.this,display_entry.class);

                intent.putExtra("entry", userEntry);
                startActivity(intent);

            }
        });

        NElogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {logout(news_judge.this);
            }
        });
    }

    private void logout(final Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Logout");
        builder.setMessage("Are you sure you want to log out?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(news_judge.this, MainActivity.class);
                startActivity(intent);
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        builder.show();
    }

    private void RetrieveList() {
        databaseReference = FirebaseDatabase.getInstance().getReference("NewsEntry");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    news_helper news_helper = ds.getValue(com.example.ejournalism.news_helper.class);
                    EntryList.add(news_helper);

                }

                String[] entryList = new String[EntryList.size()];

                for(int i = 0; i<entryList.length;i++){
                    entryList[i]= EntryList.get(i).getUser();

                }

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,entryList){

                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                        View view = super.getView(position, convertView, parent);

                        TextView textView = (TextView) view.findViewById(android.R.id.text1);
                        textView.setTextColor(Color.BLACK);

                        return view;
                    }
                };

                listView.setAdapter(arrayAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}