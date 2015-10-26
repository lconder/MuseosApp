package com.lconde.museosapp.Fragments;

import android.os.Environment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lconde.museosapp.Museo;
import com.lconde.museosapp.MyRecyclerAdapter;
import com.lconde.museosapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

public class ArtFragment extends Fragment
{

    private List<Museo> museos;
    private RecyclerView recyclerViewMuseos;
    private MyRecyclerAdapter rcAdapter;


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
        checkFile();
        museos = new ArrayList<>();
        try {
            readJson();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        recyclerViewMuseos = (RecyclerView) v.findViewById(R.id.recycler_view);
        recyclerViewMuseos.setLayoutManager(new LinearLayoutManager(v.getContext()));

        rcAdapter = new MyRecyclerAdapter(v.getContext(),museos);
        recyclerViewMuseos.setAdapter(rcAdapter);
        return v;
    }

    public boolean checkFile()
    {
        boolean bandera = false;

        File check  = new File(Environment.getExternalStorageDirectory() + File.separator + "cache.json");

        if (check.exists())
        {
            System.out.println("Archivo de cache creado");
            bandera = true;
        }else{
            System.out.println("No existe el archivo");
        }

        return bandera;
    }

    public void readJson() throws IOException, JSONException {
        File sdcard = Environment.getExternalStorageDirectory();
        File file = new File(sdcard,"cache.json");
        StringBuilder jsonBuilder = new StringBuilder();

        BufferedReader jsonReader = new BufferedReader(new FileReader(file));

        for (String line = null; (line = jsonReader.readLine()) != null;)
        {
            jsonBuilder.append(line).append("\n");
        }
        JSONTokener tokener = new JSONTokener(jsonBuilder.toString());
        JSONArray jsonArray = new JSONArray(tokener);

        for(int i=0;i<jsonArray.length();i++)
        {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String nombre =jsonObject.getString("nombre");
            String imagen = jsonObject.getString("imagen");
            int id = jsonObject.getInt("id");
            Museo temp = new Museo(nombre, id, imagen);
            museos.add(temp);
            temp = null;
        }

        System.out.println("Archivo cache: "+ jsonBuilder);
    }
}
