package com.lconde.museosapp.Fragments;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lconde.museosapp.R;

/**
 * Created by mac on 07/10/15.
 */
public class InteractiveFragment extends Fragment {

    public InteractiveFragment(){}

    public static InteractiveFragment newInstance(String param1, String param2)
    {
        InteractiveFragment fragment = new InteractiveFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {

        View v =inflater.inflate(R.layout.fragment_interactive,container,false);
        return v;
    }
}
