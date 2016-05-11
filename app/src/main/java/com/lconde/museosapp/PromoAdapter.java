package com.lconde.museosapp;


import android.app.Dialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lconde.museosapp.Classes.Evento;
import com.lconde.museosapp.Classes.Promociones;

import java.util.ArrayList;

public class PromoAdapter extends RecyclerView.Adapter<PromoAdapter.ViewHolder>
{

    private ArrayList<Promociones> promos;
    private int itemLayout;

    public PromoAdapter(ArrayList<Promociones> promos, int itemLayout) {
        this.promos = promos;
        this.itemLayout = itemLayout;
    }

    @Override
    public PromoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PromoAdapter.ViewHolder viewHolder, int position)
    {
        Promociones promo = promos.get(position);
        viewHolder.name.setText(promo.getTitulo());
        viewHolder.fecha.setText(promo.getFecha());
        viewHolder.lugar.setText(promo.getLugar());
        viewHolder.link=promo.getLink();

        switch (promo.getId()) {
            case 1:
                viewHolder.image.setImageResource(R.drawable.saladett);
                break;
            case 2:
                viewHolder.image.setImageResource(R.drawable.california);
                break;
            case 3:
                viewHolder.image.setImageResource(R.drawable.botica);
                break;
            case 4:
                viewHolder.image.setImageResource(R.drawable.wings);
                break;
            case 5:
                viewHolder.image.setImageResource(R.drawable.biblioteca);
                break;
        }

        viewHolder.itemView.setTag(promo);

    }

    @Override
    public int getItemCount() {
        return promos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements AdapterView.OnClickListener
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
        public void onClick(View v)
        {
            final Dialog dialog = new Dialog(v.getContext());
            dialog.setContentView(R.layout.custom);
            dialog.setTitle("TEST");
            Toast.makeText(v.getContext(),"LOL",Toast.LENGTH_SHORT ).show();
            TextView text = (TextView) dialog.findViewById(R.id.text);
            text.setText("Prueba");
            ImageView imageView = (ImageView) dialog.findViewById(R.id.image);
            imageView.setImageResource(R.mipmap.ic_launcher);
            dialog.dismiss();

        }

    }
}
