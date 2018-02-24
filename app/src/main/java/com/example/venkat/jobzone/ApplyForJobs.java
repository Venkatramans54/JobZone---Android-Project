package com.example.venkat.jobzone;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Venkat on 01-01-2018.
 */

public class ApplyForJobs extends AppCompatActivity {
    EditText e1, e2, e3, e4, e5, e6;
    Button b;
    TextView t;
    DatabaseReference databaseJobs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applyjob);
        e1 = (EditText) findViewById(R.id.et_name);
        e2 = (EditText) findViewById(R.id.et_degree);
        e3 = (EditText) findViewById(R.id.et_dob);
        e4 = (EditText) findViewById(R.id.et_exp);
        e5 = (EditText) findViewById(R.id.et_number);
        e6 = (EditText) findViewById(R.id.et_emailid);
        t= (TextView) findViewById(R.id.detailstextview);
        databaseJobs = FirebaseDatabase.getInstance().getReference("Job Applications");
        Bundle extras=getIntent().getExtras();
        String value = extras.getString("value");
        t.setText(value);

        b = (Button) findViewById(R.id.btn_apply);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                applyjob();

            }
        });

    }

    public void applyjob() {
        String name = e1.getText().toString().trim();
        String degree = e2.getText().toString().trim();
        String dob = e3.getText().toString().trim();
        String exp = e4.getText().toString().trim();
        String no = e5.getText().toString().trim();
        String email = e6.getText().toString().trim();

        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(degree) && !TextUtils.isEmpty(dob) && !TextUtils.isEmpty(exp) && !TextUtils.isEmpty(no) && !TextUtils.isEmpty(email)) {


            String id = databaseJobs.push().getKey();
            NewJob addDetails = new NewJob(id, name, degree, dob, exp, no, email);
            databaseJobs.child(id).setValue(addDetails);
            Toast.makeText(getApplicationContext(), "Applied", Toast.LENGTH_LONG).show();
            e1.setText("");
            e2.setText("");
            e3.setText("");
            e4.setText("");
            e5.setText("");
            e6.setText("");
        } else {
            Toast.makeText(getApplicationContext(), "Enter Complete data", Toast.LENGTH_LONG).show();
        }
    }
}
