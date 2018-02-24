package com.example.venkat.jobzone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Venkat on 28-12-2017.
 */

public class JsUpdateProfile extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5,e6,e7;
    Button b;
    DatabaseReference databaseJobs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsupdateprofile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        e1= (EditText) findViewById(R.id.jseekername);
        e2= (EditText) findViewById(R.id.jseekerdegree);
        e3= (EditText) findViewById(R.id.jseekerdob);
        e4= (EditText) findViewById(R.id.jseekerexp);
        e5= (EditText) findViewById(R.id.jseekerno);
        e6= (EditText) findViewById(R.id.jseekeremail);
        e7= (EditText) findViewById(R.id.jseekeraddress);
        databaseJobs= FirebaseDatabase.getInstance().getReference("User Profiles");
        b= (Button) findViewById(R.id.buttonupdateprofile);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateprofile();
            }
        });
    }
    public void updateprofile(){
        String name=e1.getText().toString().trim();
        String degree=e2.getText().toString().trim();
        String dob=e3.getText().toString().trim();
        String exp=e4.getText().toString().trim();
        String no=e5.getText().toString().trim();
        String email=e6.getText().toString().trim();
        String address=e7.getText().toString().trim();

        if(!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(degree)&&!TextUtils.isEmpty(dob)&&!TextUtils.isEmpty(exp)&&!TextUtils.isEmpty(no)&&!TextUtils.isEmpty(email)&&!TextUtils.isEmpty(address)){


            String id=databaseJobs.push().getKey();
            UpdateProfile addDetails= new UpdateProfile(id,name,degree,dob,exp,no,email,address);
            databaseJobs.child(id).setValue(addDetails);
            Toast.makeText(getApplicationContext(),"Profile Updated",Toast.LENGTH_LONG).show();
            e1.setText("");
            e2.setText("");
            e3.setText("");
            e4.setText("");
            e5.setText("");
            e6.setText("");
            e7.setText("");
        }
        else{
            Toast.makeText(getApplicationContext(),"Enter Complete data",Toast.LENGTH_LONG).show();
        }

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.home) {
            Intent i=new Intent(JsUpdateProfile.this,JobseekerActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
