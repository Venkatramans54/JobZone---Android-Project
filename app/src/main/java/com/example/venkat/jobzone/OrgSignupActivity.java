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
 * Created by Venkat on 15-01-2018.
 */

public class OrgSignupActivity extends AppCompatActivity {
    private Button b,b2;
    private EditText e1,e2,e3;
    private DatabaseReference dr;
    private FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orgsignup);
        b= (Button) findViewById(R.id.buttonsign);
        b2= (Button) findViewById(R.id.buttonlog);
        e1= (EditText) findViewById(R.id.editTextun);
        e2= (EditText) findViewById(R.id.editTextem);
        e3= (EditText) findViewById(R.id.editTextpw);
        database=FirebaseDatabase.getInstance();
        dr= FirebaseDatabase.getInstance().getReference("Organizations");
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent i = new Intent(getApplicationContext(), OrgloginActivity.class);
                startActivity(i);
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final User user=new User(e1.getText().toString(),e2.getText().toString(),e3.getText().toString());
                dr.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(user.getUsername()).exists()){
                            Toast.makeText(getApplicationContext(),"Sorry Username already Exist",Toast.LENGTH_LONG).show();
                        }
                        else{
                            dr.child(user.getUsername()).setValue(user);
                            Toast.makeText(getApplicationContext(),"Account created Successfully",Toast.LENGTH_LONG).show();
                            e1.setText("");
                            e2.setText("");
                            e3.setText("");
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });

    }
}
