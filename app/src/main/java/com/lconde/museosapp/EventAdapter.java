package com.lconde.museosapp;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.lconde.museosapp.Classes.Evento;

import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder>
{
    private ArrayList<Evento> eventos;
    private int itemLayout;

    public EventAdapter(ArrayList<Evento> data, int itemLayout )
    {
        this.eventos = data;
        this.itemLayout = itemLayout;
    }


    @Override
    public EventAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(EventAdapter.ViewHolder viewHolder, int position)
    {
        Evento event = eventos.get(position);
        viewHolder.name.setText(event.getTitulo());
        viewHolder.fecha.setText(event.getFecha());
        viewHolder.lugar.setText(event.getLugar());
        viewHolder.link=event.getLink();

        switch (event.getId()) {
            case 1:
                viewHolder.image.setImageResource(R.drawable.tutankamon);
                break;
            case 2:
                viewHolder.image.setImageResource(R.drawable.ballet);
                break;
            case 3:
                viewHolder.image.setImageResource(R.drawable.noche);
                break;
            case 4:
                viewHolder.image.setImageResource(R.drawable.fortuna);
                break;
            case 5:
                viewHolder.image.setImageResource(R.drawable.biblioteca);
                break;
        }

        viewHolder.itemView.setTag(event);


    }

    @Override
    public int getItemCount()  {
        return eventos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements AdapterView
    .OnClickListener
    {

        public ImageView image;
        public TextView name;
        public TextView fecha;
        public TextView lugar;
        public String link;

        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            image = (ImageView) itemView.findViewById(R.id.image);
            name = (TextView) itemView.findViewById(R.id.titulo);
            fecha = (TextView) itemView.findViewById(R.id.fecha);
            lugar = (TextView) itemView.findViewById(R.id.lugar);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
