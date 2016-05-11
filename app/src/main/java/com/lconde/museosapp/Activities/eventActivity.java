package com.lconde.museosapp.Activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.lconde.museosapp.MyPagerAdapter;
import com.lconde.museosapp.R;

public class eventActivity extends AppCompatActivity
{

    ViewPager pager;
    PagerTabStrip tab_strp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        MyPagerAdapter mpager =  new MyPagerAdapter(getSupportFragmentManager());
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(mpager);
        tab_strp= (PagerTabStrip) findViewById(R.id.tab_strip);
        tab_strp.setTextColor(Color.WHITE);



    }

}
