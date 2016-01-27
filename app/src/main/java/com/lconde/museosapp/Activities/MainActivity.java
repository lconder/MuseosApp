package com.lconde.museosapp.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.lconde.museosapp.Fragments.ArtFragment;
import com.lconde.museosapp.Fragments.HistoryFragment;
import com.lconde.museosapp.Fragments.InteractiveFragment;
import com.lconde.museosapp.R;
import com.lconde.museosapp.SlidingTabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity
{
    Toolbar toolbar;
    ViewPager mPager;
    SlidingTabLayout mTabs;
    String server;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        mTabs = (SlidingTabLayout) findViewById(R.id.tabs);
        mTabs.setDistributeEvenly(true);
        mTabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.md_white_1000);
            }
        });
        mTabs.setCustomTabView(R.layout.custom_tab_view, R.id.tabText);
        mTabs.setViewPager(mPager);


        if (Build.VERSION.SDK_INT < 19) {
            FrameLayout statusBar = (FrameLayout) findViewById(R.id.statusBar);
            statusBar.setVisibility(View.GONE);
            /*ViewGroup.LayoutParams layoutParams = statusBar.getLayoutParams();
            layoutParams.height=0;*/
        }

        server  = getResources().getString(R.string.server);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id == R.id.action_id)
        {
            SharedPreferences sp=sp = getSharedPreferences("datos", Context.MODE_PRIVATE);
            if(sp.getBoolean("bandera",false))
            {
                Intent intent = new Intent(getApplicationContext(),ProfileActivity.class);
                startActivity(intent);
            }else {
                Intent intent = new Intent(MainActivity.this, IdentifyActivity.class);
                startActivity(intent);
            }
        }else if (id == R.id.action_settings)
        {
            Intent intent = new Intent(MainActivity.this, InfoActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void onStart()
    {
        super.onStart();
        // Create request queue
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        //  Create json array request
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET,server,new Response.Listener<JSONArray>()
        {

            public void onResponse(JSONArray jsonArray)
            {
                BufferedOutputStream bos;
                File cache = new File(Environment.getExternalStorageDirectory() + File.separator + "cache.json");
                try {
                    cache.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try{
                    bos = new BufferedOutputStream(new FileOutputStream(cache));
                    bos.write(jsonArray.toString().getBytes());
                    bos.flush();
                    bos.close();

                }catch (FileNotFoundException e)
                {
                    e.printStackTrace();
                }catch(IOException a)
                {
                    a.printStackTrace();
                }
                finally{
                    System.gc();
                }

                for(int i=0;i<jsonArray.length();i++)
                {
                    try
                    {
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("Error", "Unable to parse json array "+volleyError.toString());
            }
        });
        // add json array request to the request queue
        requestQueue.add(jsonArrayRequest);
    }

    class MyPagerAdapter extends FragmentPagerAdapter {
        int icons[] = {R.mipmap.arte, R.mipmap.interactivo, R.mipmap.historia};
        FragmentManager fragmentManager;


        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
            fragmentManager = null;
        }

        @Override
        public Fragment getItem(int position)
        {
            Fragment fragment = null;

            switch (position)
            {
                case 0:
                    fragment = ArtFragment.newInstance("", "");
                    break;
                case 1:
                    fragment = InteractiveFragment.newInstance("", "");
                    break;
                case 2:
                    fragment = HistoryFragment.newInstance("", "");
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount()
        {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Drawable drawable = getResources().getDrawable(icons[position]);
            drawable.setBounds(0, 0, 88, 88);
            ImageSpan imageSpan = new ImageSpan(drawable);
            SpannableString spannableString = new SpannableString(" ");
            spannableString.setSpan(imageSpan, 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return spannableString;
        }

    }

}
