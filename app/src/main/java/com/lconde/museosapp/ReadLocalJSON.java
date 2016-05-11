package com.lconde.museosapp;


import android.content.Context;
import android.widget.Toast;

import com.lconde.museosapp.Classes.Evento;
import com.lconde.museosapp.Classes.Promociones;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ReadLocalJSON
{
    private String json="";
    private String archivo = "";
    private ArrayList<Evento> events = new ArrayList<>();
    private ArrayList<Promociones> promos = new ArrayList<>();
    private BufferedReader bufferedReader;
    private StringBuilder builder;

    public ArrayList<Evento> getEvents(Context c)
    {

        try
        {
            builder = new StringBuilder();
            archivo = "eventos.json";
            bufferedReader = new BufferedReader(new InputStreamReader(c.getAssets().open(archivo)));
            String line="";

            while((line = bufferedReader.readLine()) != null)
            {
                builder.append(line);
            }

            bufferedReader.close();
            json = builder.toString();

            JSONArray jsonArray = new JSONArray(json);


                for (int index = 0; index < jsonArray.length(); index++)
                {
                    Evento event = new Evento();

                    JSONObject jsonObject = jsonArray.getJSONObject(index);
                    event.setId(jsonObject.getInt("id"));
                    event.setTitulo(jsonObject.getString("titulo"));
                    event.setDescripcion(jsonObject.getString("descripcion"));
                    event.setLugar(jsonObject.getString("lugar"));
                    event.setFecha(jsonObject.getString("fecha"));
                    event.setLink(jsonObject.getString("link"));
                    event.setImagen(jsonObject.getString("image"));
                    events.add(event);
                }

                for (int index = 0; index < jsonArray.length(); index++)
                {
                    Promociones promo = new Promociones();

                    JSONObject jsonObject = jsonArray.getJSONObject(index);
                    promo.setId(jsonObject.getInt("id"));
                    promo.setTitulo(jsonObject.getString("titulo"));
                    promo.setDescripcion(jsonObject.getString("descripcion"));
                    promo.setLugar(jsonObject.getString("lugar"));
                    promo.setFecha(jsonObject.getString("fecha"));
                    promo.setLink(jsonObject.getString("link"));
                    promo.setImagen(jsonObject.getString("image"));
                    promos.add(promo);
                }





        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(c,"No se pudo obtener los datos", Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(c, "No se pudo obtener los datos", Toast.LENGTH_SHORT).show();
        }

            return events;
    }

    public ArrayList<Promociones> getPromos(Context c)
    {

        try
        {
            builder = new StringBuilder();
            archivo = "promos.json";
            bufferedReader = new BufferedReader(new InputStreamReader(c.getAssets().open(archivo)));
            String line="";

            while((line = bufferedReader.readLine()) != null)
            {
                builder.append(line);
            }

            bufferedReader.close();
            json = builder.toString();

            JSONArray jsonArray = new JSONArray(json);


            for (int index = 0; index < jsonArray.length(); index++)
            {
                Promociones promo = new Promociones();

                JSONObject jsonObject = jsonArray.getJSONObject(index);
                promo.setId(jsonObject.getInt("id"));
                promo.setTitulo(jsonObject.getString("titulo"));
                promo.setDescripcion(jsonObject.getString("descripcion"));
                promo.setLugar(jsonObject.getString("lugar"));
                promo.setFecha(jsonObject.getString("fecha"));
                promo.setLink(jsonObject.getString("link"));
                promo.setImagen(jsonObject.getString("image"));
                promos.add(promo);
            }





        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(c,"No se pudo obtener los datos", Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(c, "No se pudo obtener los datos", Toast.LENGTH_SHORT).show();
        }

        return promos;

    }


}
