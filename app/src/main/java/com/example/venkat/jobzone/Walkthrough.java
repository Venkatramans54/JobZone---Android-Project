package com.example.venkat.jobzone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Venkat on 07-01-2018.
 */

public class Walkthrough extends AppCompatActivity {
    private ViewPager mslideviewpager;
    private LinearLayout mdotlayout;
    SliderAdapter sliderAdapter;
    private TextView[] mdots;
    Button next,prev;
    int currentpage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walkthrough);

        mslideviewpager= (ViewPager) findViewById(R.id.slideviewpager);
        mdotlayout= (LinearLayout) findViewById(R.id.dotslayout);
        next= (Button) findViewById(R.id.btnfrwd);
        prev= (Button) findViewById(R.id.btnback);

        sliderAdapter =new SliderAdapter(this);
        mslideviewpager.setAdapter(sliderAdapter);
        adddotsindicator(0);
        mslideviewpager.addOnPageChangeListener(viewlistener);

    }
    public void adddotsindicator(int position){
        mdots=new TextView[6];
        mdotlayout.removeAllViews();
        for(int i=0;i<mdots.length;i++){
            mdots[i]=new TextView(this);
            mdots[i].setText(Html.fromHtml("&#8226"));
            mdots[i].setTextSize(50);
            mdots[i].setTextColor(getResources().getColor(R.color.nadot));
            mdotlayout.addView(mdots[i]);
        }
        if(mdots.length>0){
            mdots[position].setTextColor(getResources().getColor(R.color.adot));
        }
    }
    ViewPager.OnPageChangeListener viewlistener=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int i) {
            adddotsindicator(i);
            currentpage=i;
            if(i==0){
                next.setEnabled(true);
                prev.setEnabled(false);
                prev.setVisibility(View.INVISIBLE);

                next.setText("Next");
                prev.setText("");
            }
            else if(i==mdots.length-1){
                next.setEnabled(true);
                prev.setEnabled(true);
                prev.setVisibility(View.VISIBLE);

                next.setText("Finish");
                prev.setText("Back");
                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i=new Intent(Walkthrough.this,MainActivity.class);
                        startActivity(i);

                    }
                });

            }
            else{
                next.setEnabled(true);
                prev.setEnabled(true);
                prev.setVisibility(View.VISIBLE);

                next.setText("Next");
                prev.setText("Back");
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
