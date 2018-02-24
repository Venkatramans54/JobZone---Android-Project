package com.example.venkat.jobzone;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by Venkat on 25-12-2017.
 */

public class OrganizationActivity extends AppCompatActivity {
    private DatabaseReference dr;
    private SwipeRefreshLayout srl;
    private ListView listView;

    private ArrayList<String> listitem=new ArrayList<>();

    private ArrayList<String> itemkey=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_org);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dr= FirebaseDatabase.getInstance().getReference().child("For Retrieval");
        listView= (ListView) findViewById(R.id.listview001);


        srl= (SwipeRefreshLayout) findViewById(R.id.swiperefreshlayout2);
        srl.setColorSchemeResources(R.color.swipe1,R.color.swipe2,R.color.swipe3);

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollstate) {

            }

            @Override
            public void onScroll(AbsListView absListView, int firstvisibleitem, int visibleitemcount, int totalitemcount) {

                int toprowverticalposition=(listView==null|| listView.getChildCount()==0)?0:listView.getChildAt(0).getTop();
                srl.setEnabled(firstvisibleitem==0&&toprowverticalposition>=0);

            }
        });

        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srl.setRefreshing(true);
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        srl.setRefreshing(false);
                        Toast.makeText(OrganizationActivity.this, "Reloaded the Page...", Toast.LENGTH_SHORT).show();
                    }
                },3000) ;         }
        });


        final ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listitem);
        listView.setAdapter(arrayAdapter);

        dr.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String value=dataSnapshot.getValue(String.class);
                listitem.add(value);

                String key=dataSnapshot.getKey();
                itemkey.add(key);

                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                String value=dataSnapshot.getValue(String.class);
                String key=dataSnapshot.getKey();

                int index=itemkey.indexOf(key);

                listitem.set(index,value);
                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.orgmenu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.orgexit) {
            finish();
            return true;
        }
        if (id == R.id.orglogout) {
            finish();
            return true;
        }
        if (id == R.id.postjobs) {
            Intent i=new Intent(OrganizationActivity.this,OrgPostJobs.class);
            startActivity(i);
            return true;
        }
        if (id == R.id.home) {
            Intent i=new Intent(OrganizationActivity.this,OrgloginActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
