package com.example.ejournalism;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText userPangalan,password;
    String USERNAME;

    Button Login;

    FirebaseDatabase UsersDatabase;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userPangalan = findViewById(R.id.Username);
        password = findViewById(R.id.Password);

        Login =  (Button) findViewById(R.id.Login);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(userPangalan.getText().toString())){
                    Toast.makeText(MainActivity.this, "Please Enter A UserName", Toast.LENGTH_SHORT).show();

                }
                else{
                    final DatabaseReference mref = FirebaseDatabase.getInstance().getReference().child("users");
                    mref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String searchUser = userPangalan.getText().toString();
                            String searchPass= password.getText().toString();


                            if(dataSnapshot.child(searchUser).exists()){
                                String passwordMo = dataSnapshot.child(searchUser).child("password").getValue().toString();
                                if(searchPass.equals(passwordMo)){

                                    if((dataSnapshot.child(searchUser).child("userType").getValue().toString()).equals("Admin")){

                                        openCategories();
                                    }
                                    else if((dataSnapshot.child(searchUser).child("userType").getValue().toString()).equals("Participant")){

                                        gotoParticipants();

                                    }
                                    else if((dataSnapshot.child(searchUser).child("userType").getValue().toString()).equals("News Writing Judge")){
                                        gotoNewsJudge();

                                    }
                                    else if((dataSnapshot.child(searchUser).child("userType").getValue().toString()).equals("Feature Writing Judge")){
                                        gotoFeatureJudge();

                                    }
                                    else if((dataSnapshot.child(searchUser).child("userType").getValue().toString()).equals("Sports Writing Judge")){
                                        gotoSportsJudge();

                                    } else if((dataSnapshot.child(searchUser).child("userType").getValue().toString()).equals("Editorial Writing Judge")){
                                        gotoEditorialJudge();

                                    }
                                    else if((dataSnapshot.child(searchUser).child("userType").getValue().toString()).equals("Science Writing Judge")){
                                        gotoScienceJudge();

                                    }
                                    else if((dataSnapshot.child(searchUser).child("userType").getValue().toString()).equals("Photo Journalism Judge")){
                                        gotoPhotoJudge();

                                    }



                                }
                                else {
                                    Toast.makeText(MainActivity.this,"Wrong Password",Toast.LENGTH_SHORT).show();
                                }

                            }

                            else{
                                Toast.makeText(MainActivity.this,"USER NOT REGISTERED",Toast.LENGTH_SHORT).show();

                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }

            }
        });
    }
    public void openCategories(){
        Intent intent = new Intent(this, Admin.class);
        String username= userPangalan.getText().toString();
        intent.putExtra("Username",username);
        startActivity(intent);
    }

    public void gotoParticipants(){
        String username= userPangalan.getText().toString();

        Intent intent = new Intent(this, Categories.class);
        intent.putExtra("Username",username);
        startActivity(intent);
    }
    public void gotoEditorialJudge(){
        Intent intent = new Intent(this, editorial_judge.class);
        String username= userPangalan.getText().toString();
        intent.putExtra("Username",username);
        startActivity(intent);
    }
    public void gotoFeatureJudge(){
        Intent intent = new Intent(this, feature_judge.class);
        String username= userPangalan.getText().toString();
        intent.putExtra("Username",username);
        startActivity(intent);
    }
    public void gotoNewsJudge(){
        Intent intent = new Intent(this, news_judge.class);
        String username= userPangalan.getText().toString();
        intent.putExtra("Username",username);
        startActivity(intent);
    }
    public void gotoPhotoJudge(){
        Intent intent = new Intent(this, photo_judge.class);
        String username= userPangalan.getText().toString();
        intent.putExtra("Username",username);
        startActivity(intent);
    }
    public void gotoScienceJudge(){
        Intent intent = new Intent(this, science_judge.class);
        String username= userPangalan.getText().toString();
        intent.putExtra("Username",username);
        startActivity(intent);
    }

    public void gotoSportsJudge(){
        Intent intent = new Intent(this, sports_judge.class);
        String username= userPangalan.getText().toString();
        intent.putExtra("Username",username);
        startActivity(intent);
    }
}