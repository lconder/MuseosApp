package com.lconde.museosapp.Fragments;



import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.lconde.museosapp.R;

public class ArtFragment extends Fragment
{
    public ArtFragment(){}

    public static ArtFragment newInstance(String param1, String param2)
    {
        ArtFragment fragment = new ArtFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {

        View v =inflater.inflate(R.layout.fragment_art,container,false);
        return v;
    }
}
