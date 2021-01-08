package com.example.ejournalism;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Categories extends AppCompatActivity {

    Button editorial,feature,news,photo,science,sports,logout;
    String USERN;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        editorial = (Button) findViewById(R.id.Editorial_Writing);
        feature = (Button) findViewById(R.id.Feature_Writing);
        news = (Button) findViewById(R.id.News_Writing);
        photo = (Button) findViewById(R.id.Photo_Writing);
        science = (Button) findViewById(R.id.Science_Writing);
        sports = (Button) findViewById(R.id.Sports_Writing);
        logout = (Button) findViewById(R.id.Logout);

        String use = getIntent().getStringExtra("Username");
        USERN = use;
        DatabaseReference user = FirebaseDatabase.getInstance().getReference("EditorialEntry");
        DatabaseReference feat = FirebaseDatabase.getInstance().getReference("FeatureEntry");
        DatabaseReference phot = FirebaseDatabase.getInstance().getReference("PhotoEntry");
        DatabaseReference newz = FirebaseDatabase.getInstance().getReference("NewsEntry");
        DatabaseReference spot = FirebaseDatabase.getInstance().getReference("SportsEntry");
        DatabaseReference sci = FirebaseDatabase.getInstance().getReference("ScienceEntry");

        user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.child(USERN).exists()){
                    editorial.setEnabled(false);
                }

                else{
                    editorial.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(Categories.this, editorialpage.class);
                            intent.putExtra("UserName",USERN);
                            startActivity(intent);

                        }
                    });

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        feat.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.child(USERN).exists()){
                    feature.setEnabled(false);
                }

                else{
                    feature.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(Categories.this, featurepage.class);
                            intent.putExtra("UserName",USERN);
                            startActivity(intent);

                        }
                    });

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        newz.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.child(USERN).exists()){
                    news.setEnabled(false);
                }

                else{
                    news.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(Categories.this, newspage.class);
                            intent.putExtra("UserName",USERN);
                            startActivity(intent);

                        }
                    });

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        phot.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.child(USERN).exists()){
                    photo.setEnabled(false);
                }

                else{
                    photo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(Categories.this, photopage.class);
                            intent.putExtra("UserName",USERN);
                            startActivity(intent);

                        }
                    });

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        sci.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(USERN).exists()){
                    science.setEnabled(false);
                }

                else{
                    science.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(Categories.this, sciencepage.class);
                            intent.putExtra("UserName",USERN);
                            startActivity(intent);

                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        spot.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.child(USERN).exists()){
                    sports.setEnabled(false);
                }

                else{
                    sports.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(Categories.this, sportspage.class);
                            intent.putExtra("UserName",USERN);
                            startActivity(intent);

                        }
                    });

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout(Categories.this);
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
                Intent intent = new Intent(Categories.this, MainActivity.class);
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
}





