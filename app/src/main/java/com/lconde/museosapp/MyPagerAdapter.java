package com.lconde.museosapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lconde.museosapp.Fragments.eventFragment;
import com.lconde.museosapp.Fragments.promoFragment;

import java.util.Locale;

public class MyPagerAdapter extends FragmentPagerAdapter
{
    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position)
    {
        switch(position)
        {
            case 0:
                eventFragment t1 = new eventFragment();
                return t1;
            case 1:
                promoFragment t2 = new promoFragment();
                return t2;
        }
        return null;
    }



    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        Locale l = Locale.getDefault();
        switch (position) {
            case 0:
                return "Evento";
            case 1:

                return "Promociones";
        }
        return null;
    }
}
