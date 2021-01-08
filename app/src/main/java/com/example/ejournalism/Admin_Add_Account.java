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
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.atomic.AtomicBoolean;

public class Admin_Add_Account extends AppCompatActivity {

    Button Cancel;
    Button register;
    EditText regName,regEmail,regPassword;
    Spinner mySpinner;

    FirebaseDatabase UsersDatabase;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__add__account);

         mySpinner = (Spinner) findViewById(R.id.regspinner);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(Admin_Add_Account.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.user_types));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);



        Cancel = (Button) findViewById(R.id.RegCancel);
        register = (Button) findViewById(R.id.Register);

        regName =  findViewById(R.id.reg_Name);
        regEmail =  findViewById(R.id.reg_Email);
        regPassword = findViewById(R.id.reg_Password);


        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_Add_Account.this, Admin.class);
                startActivity(intent);

            }
        });

//        register.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                UsersDatabase = FirebaseDatabase.getInstance();
//                reference = UsersDatabase.getReference("users");
//
//                String userName = regName.getText().toString();
//                String email = regEmail.getText().toString();
//                String password = regPassword.getText().toString();
//                String userType = mySpinner. getSelectedItem().toString();
//
//                userHelper userHelper = new userHelper(userName, email, password, userType);
//
//                reference.child(userName).setValue(userHelper);
//
//            }
//        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty((regName.getText().toString()))){
                    Toast.makeText(Admin_Add_Account.this, "Please Enter a Username", Toast.LENGTH_SHORT).show();

                }

                else{
                    final DatabaseReference mref = FirebaseDatabase.getInstance().getReference().child("users");

                    mref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String searchUser = regName.getText().toString();


                            if(dataSnapshot.child(searchUser).exists()){
                                Toast.makeText(Admin_Add_Account.this,"USERNAME ALREADY EXISTS",Toast.LENGTH_SHORT).show();
                            }

                            else{

                                UsersDatabase = FirebaseDatabase.getInstance();
                                reference = UsersDatabase.getReference("users");

                                 String userName = regName.getText().toString();
                                 String email = regEmail.getText().toString();
                                 String password = regPassword.getText().toString();
                                 String userType = mySpinner. getSelectedItem().toString();

                                 userHelper userHelper = new userHelper(userName, email, password, userType);

                                 reference.child(userName).setValue(userHelper);

                                Toast.makeText(Admin_Add_Account.this,"USER REGISTERED",Toast.LENGTH_LONG).show();

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
}