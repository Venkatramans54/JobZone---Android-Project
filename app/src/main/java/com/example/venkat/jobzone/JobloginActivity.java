package com.example.venkat.jobzone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Venkat on 25-12-2017.
 */

public class JobloginActivity extends AppCompatActivity {
    Button b,b2;
    private EditText e1,e2;
    private DatabaseReference dr;
    private FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jlogin);
        e1= (EditText) findViewById(R.id.etusername);
        e2= (EditText) findViewById(R.id.etpassword);
        database=FirebaseDatabase.getInstance();
        dr= FirebaseDatabase.getInstance().getReference("Job Seekers");
        b= (Button) findViewById(R.id.buttonlogin);
        b2= (Button) findViewById(R.id.buttonsignup);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(JobloginActivity.this,JobSeekerSignupActivity.class);
                startActivity(i);
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signin(e1.getText().toString(),e2.getText().toString());

            }
            private void signin(final String username,final String password) {
                dr.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(username).exists()){
                            if(!username.isEmpty()){
                                User login=dataSnapshot.child(username).getValue(User.class);
                                if(login.getPassword().equals(password)){
                                    e1.setText("");
                                    e2.setText("");
                                    Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();
                                    Intent i=new Intent(JobloginActivity.this,JobseekerActivity.class);
                                    startActivity(i);
                                }
                                else {
                                    Toast.makeText(getApplicationContext(),"Sorry Invalid Credentials",Toast.LENGTH_LONG).show();
                                }
                            }

                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Not Registered",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            }
        });
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Intent i=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
        finish();
    }



}
