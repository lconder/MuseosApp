package com.lconde.museosapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.lconde.museosapp.Activities.detailActivity;
import com.lconde.museosapp.Classes.Museo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder>
{
    private List<Museo> museos;
    private Context context;

    public MyRecyclerAdapter(Context context, List<Museo> museos)
    {
        this.museos = museos;
        this.context = context;
    }

    @Override
    public MyRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row,null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        String estado = "";

        if(museos.get(position).isOpen())
            estado = "Abierto";
        else
            estado = "Cerrado";



        holder.museoName.setText(museos.get(position).getNombre());
        holder.museoStatus.setText(estado);
        Picasso.with(context).load(museos.get(position).getImagen()).into(holder.museoPhoto);
        holder.nombreMuseo = museos.get(position).getNombre();
        holder.imagenMuseo = museos.get(position).getImagen();
        holder.telefonoMuseo = museos.get(position).getTelefono();
        holder.direccionMuseo = museos.get(position).getDireccion();
        holder.descripcionCortaMuseo = museos.get(position).getDescripcionCorta();
        holder.facebookMuseo = museos.get(position).getFacebook();
        holder.facebookIdMuseo = museos.get(position).getFacebookId();
        holder.twitterMuseo = museos.get(position).getTwitter();
        holder.instagramMuseo = museos.get(position).getInstagram();
        holder.webMuseo = museos.get(position).getWeb();
        holder.latitudMuseo = museos.get(position).getLatitud();
        holder.longitudMuseo = museos.get(position).getLongitud();
        holder.twitterId = museos.get(position).getTwitterId();
    }

    @Override
    public int getItemCount() {
        return this.museos.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder  implements AdapterView.OnClickListener
    {

        public TextView museoName;
        public TextView museoStatus;
        public ImageView museoPhoto;

        public String nombreMuseo;
        public String imagenMuseo;
        public String telefonoMuseo;
        public String direccionMuseo;
        public String descripcionCortaMuseo;
        public String facebookMuseo;
        public String facebookIdMuseo;
        public String twitterMuseo;
        public String instagramMuseo;
        public String webMuseo;
        public String latitudMuseo;
        public String longitudMuseo;
        public String twitterId;

        public ViewHolder(View view) {
            super(view);
            museoName = (TextView)itemView.findViewById(R.id.museo_name);
            museoStatus = (TextView)itemView.findViewById(R.id.museo_status);
            museoPhoto = (ImageView)itemView.findViewById(R.id.circleView);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v)
        {
            Intent intent = new Intent(context,detailActivity.class);
            intent.putExtra("nombreMuseo",nombreMuseo);
            intent.putExtra("imagenMuseo",imagenMuseo);
            intent.putExtra("telefonoMuseo",telefonoMuseo);
            intent.putExtra("descripcionCortaMuseo",descripcionCortaMuseo);
            intent.putExtra("facebookMuseo",facebookMuseo);
            intent.putExtra("facebookIdMuseo",facebookIdMuseo);
            intent.putExtra("twitterMuseo",twitterMuseo);
            intent.putExtra("instagramMuseo",instagramMuseo);
            intent.putExtra("webMuseo",webMuseo);
            intent.putExtra("latitud",latitudMuseo);
            intent.putExtra("longitud",longitudMuseo);
            intent.putExtra("twitterId",twitterId);
            v.getContext().startActivity(intent);
        }
    }

}
