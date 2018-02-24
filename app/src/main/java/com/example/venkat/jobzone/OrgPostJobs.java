package com.example.venkat.jobzone;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Venkat on 28-12-2017.
 */

public class OrgPostJobs extends AppCompatActivity{
    EditText e1,e2,e3,e4;
    Button b;
    private ProgressDialog progress;
    DatabaseReference databaseJobs, forretrieval;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orgpostjobs);
        e1= (EditText) findViewById(R.id.cmpname);
        e2= (EditText) findViewById(R.id.jobpost);
        e3= (EditText) findViewById(R.id.placeofwork);
        e4= (EditText) findViewById(R.id.jobdesc);
        progress=new ProgressDialog(this);
        databaseJobs= FirebaseDatabase.getInstance().getReference("Posted Jobs");
        forretrieval= FirebaseDatabase.getInstance().getReference("For Retrieval");
        b= (Button) findViewById(R.id.postbutton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDetails();
                e1.setText(null);
                e2.setText(null);
                e3.setText(null);
                e4.setText(null);
            }
        });
    }
    public void addDetails(){

        progress.setMessage("Posting Data");
        progress.show();
        String name=e1.getText().toString().trim();
        String post=e2.getText().toString().trim();
        String pow=e3.getText().toString().trim();
        String desc=e4.getText().toString().trim();


        String completedata="Company Name: "+name+"\nJob Post: "+post+"\nPlace of Work: "+pow+"\nJob Description: "+desc;

        if(!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(post)&&!TextUtils.isEmpty(pow)&&!TextUtils.isEmpty(desc)){


            String id=databaseJobs.push().getKey();
            String id2=forretrieval.push().getKey();
            PostJobs addDetails= new PostJobs(id,name,post,pow,desc);
            databaseJobs.child(id).setValue(addDetails);
            forretrieval.child(id2).setValue(completedata);
            progress.dismiss();
            Toast.makeText(getApplicationContext(),"Details Added",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(getApplicationContext(),"Enter Complete data",Toast.LENGTH_LONG).show();
        }

    }
}
