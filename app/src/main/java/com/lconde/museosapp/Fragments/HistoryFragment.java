package com.lconde.museosapp.Fragments;

import android.content.Intent;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.lconde.museosapp.Activities.MapsActivity;
import com.lconde.museosapp.Horario;
import com.lconde.museosapp.Museo;
import com.lconde.museosapp.MyRecyclerAdapter;
import com.lconde.museosapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment
{

    private List<Museo> museos;
    private List<Horario> horarios;
    private RecyclerView recyclerViewMuseos;
    private MyRecyclerAdapter rcAdapter;
    private FloatingActionButton fab1;
    private FloatingActionButton fab2;
    private FloatingActionButton fab3;

    public HistoryFragment(){}

    public static HistoryFragment newInstance(String param1, String param2)
    {
        HistoryFragment fragment = new HistoryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {

        View v =inflater.inflate(R.layout.fragment_history,container,false);
        checkFile();
        museos = new ArrayList<>();
        try {
            readJson();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        fab1 = (FloatingActionButton) v.findViewById(R.id.fab1);
        fab2 = (FloatingActionButton) v.findViewById(R.id.fab2);
        fab3 = (FloatingActionButton) v.findViewById(R.id.fab3);

        recyclerViewMuseos = (RecyclerView) v.findViewById(R.id.recycler_view);
        recyclerViewMuseos.setLayoutManager(new LinearLayoutManager(v.getContext()));

        rcAdapter = new MyRecyclerAdapter(v.getContext(),museos);
        recyclerViewMuseos.setAdapter(rcAdapter);


        final FloatingActionMenu menu1 = (FloatingActionMenu) v.findViewById(R.id.menu1);

        menu1.setOnMenuButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (menu1.isOpened()) {
                    Toast.makeText(v.getContext(), menu1.getMenuButtonLabelText(), Toast.LENGTH_SHORT).show();
                }

                menu1.toggle(true);
            }
        });

        fab1.setOnClickListener(clickListener);
        fab2.setOnClickListener(clickListener);
        fab3.setOnClickListener(clickListener);

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
            String telefono = jsonObject.getString("telefono");
            String direccion = jsonObject.getString("direccion");
            String latitud = jsonObject.getString("latitud");
            String longitud = jsonObject.getString("longitud");
            String descripcionCorta = jsonObject.getString("descripcionCorta");
            String descripcionLarga = jsonObject.getString("descripcionLarga");
            String facebook = jsonObject.getString("facebook");
            String facebookId = jsonObject.getString("facebookid");
            String twitter = jsonObject.getString("twitter");
            String instagram = jsonObject.getString("instagram");
            String web = jsonObject.getString("web");
            horarios = creaHorarios(jsonObject.getJSONArray("horarios"));
            int id = jsonObject.getInt("id");
            if(jsonObject.getString("categoria").equals("historia"))
            {
                Museo temp = new Museo (nombre,id,imagen,telefono,direccion, latitud,  longitud,  descripcionCorta,  descripcionLarga,  facebook,  facebookId, twitter,  instagram,  web,horarios);
                museos.add(temp);
                temp = null;
            }

        }

        System.out.println("Archivo cache: " + jsonBuilder);
    }

    public List<Horario> creaHorarios(JSONArray jsonArray)
    {
        List<Horario> temp = new ArrayList<Horario>();
        for (int i=0;i<jsonArray.length();i++)
        {
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String dia = jsonObject.getString("dia");
                String horaApertura = jsonObject.getString("horaApertura");
                String horaCierre = jsonObject.getString(("horaCierre"));
                //System.out.println("Horario Dia: "+dia+" "+horaApertura+" a "+horaCierre);
                Horario tempH = new Horario(dia,horaApertura,horaCierre);
                temp.add(tempH);
                tempH = null;
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return temp;
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String text = "";

            switch (v.getId()) {
                case R.id.fab1:
                    text = fab1.getLabelText();
                    Intent intent = new Intent(getActivity().getApplicationContext(),MapsActivity.class);
                    startActivity(intent);
                    break;
                case R.id.fab2:
                    text = fab2.getLabelText();
                    break;
                case R.id.fab3:
                    text = fab3.getLabelText();
                    break;
            }

            Toast.makeText(v.getContext(), text, Toast.LENGTH_SHORT).show();
        }
    };

}
