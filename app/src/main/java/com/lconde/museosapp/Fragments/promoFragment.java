package com.lconde.museosapp.Fragments;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lconde.museosapp.Classes.Promociones;
import com.lconde.museosapp.EventAdapter;
import com.lconde.museosapp.PromoAdapter;
import com.lconde.museosapp.R;
import com.lconde.museosapp.ReadLocalJSON;

import java.util.ArrayList;

public class promoFragment extends Fragment
{

    ArrayList<Promociones> promos;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_promo,container,false);
        ReadLocalJSON readLocalJSON = new ReadLocalJSON();
        promos = readLocalJSON.getPromos(getActivity().getApplicationContext());
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new PromoAdapter(promos, R.layout.row));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        return view;
    }

}
