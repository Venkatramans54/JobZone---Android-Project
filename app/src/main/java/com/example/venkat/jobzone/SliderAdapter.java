package com.example.venkat.jobzone;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Venkat on 07-01-2018.
 */

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;
    public SliderAdapter(Context context){
        this.context=context;
    }
    public int[] slide_images={R.drawable.w1, R.drawable.w2, R.drawable.w3, R.drawable.w4, R.drawable.w5, R.drawable.w6};
    public String[] slide_heading={"JobZone","Registration","Job Post Upload","Find Suitable Job","Apply for Job","Get Placed"};
    public String[] slide_description={"This Application lets the job seeker to find companies and vacancies in them. Get the details of vacant position and apply for the job","What all the Users of this application must do is to register their professional details and create an account in this application","The Registered Companies upload the details about the vacant job posts in their Organization, which visible to all he users of this application","The Registered Job Seekers of this application search for their suitable jobs posted by the Registered Organizations","Once suitable Job satisfying the Job Seekers' need is found, they can apply for that vacant post","After Successfully Completing their Interview Processes, Job Seeekers finally becomes the empoyee of the Organization"};

    @Override
    public int getCount() {
        return slide_heading.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (RelativeLayout) object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.slide_layout,container,false);
        ImageView slideimageview= (ImageView) view.findViewById(R.id.imageView);
        TextView slideheading= (TextView) view.findViewById(R.id.slideheader);
        TextView slidedesc= (TextView) view.findViewById(R.id.slidedesc);

        slideimageview.setImageResource(slide_images[position]);
        slideheading.setText(slide_heading[position]);
        slidedesc.setText(slide_description[position]);
        container.addView(view);

        return  view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout)object);
    }
}
