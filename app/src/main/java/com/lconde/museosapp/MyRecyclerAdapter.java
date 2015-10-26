package com.lconde.museosapp;

import android.content.Context;
import android.media.session.PlaybackState;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.CustomViewHolder>
{
    private List<Museo> museos;
    private Context context;

    public MyRecyclerAdapter(Context context, List<Museo> museos)
    {
        this.museos = museos;
        this.context = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int i)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row,null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position)
    {
        holder.museoName.setText(museos.get(position).getNombre());
        holder.museoStatus.setText("Abierto o Cerrado");
        Picasso.with(context).load(museos.get(position).getImagen()).into(holder.museoPhoto);
        //holder.museoPhoto.setImageResource(museos.get(position).getImagen());
    }

    @Override
    public int getItemCount() {
        return this.museos.size();
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder {

        public TextView museoName;
        public TextView museoStatus;
        public ImageView museoPhoto;

        public CustomViewHolder(View view) {
            super(view);
            museoName = (TextView)itemView.findViewById(R.id.museo_name);
            museoStatus = (TextView)itemView.findViewById(R.id.museo_status);
            museoPhoto = (ImageView)itemView.findViewById(R.id.circleView);
        }
    }

}
