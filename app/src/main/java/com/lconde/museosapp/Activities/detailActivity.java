    package com.lconde.museosapp.Activities;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lconde.museosapp.R;
import com.squareup.picasso.Picasso;

public class detailActivity extends AppCompatActivity
{

    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbar;
    FloatingActionButton fab;
    Button buttonPhone;
    Button buttonWeb;
    Button buttonMap;
    Button buttonFacebook;
    Button buttonTwitterInsta;
    TextView textViewPhone;
    TextView textViewDescripcion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        final Bundle extras = getIntent().getExtras();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(extras.getString("nombreMuseo"));
        collapsingToolbar.setContentScrimColor(R.color.md_pink_700);

        fab=(FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Se ha registrado tu visita. Gracias", Snackbar.LENGTH_SHORT).show();
            }
        });
        ImageView header = (ImageView) findViewById(R.id.img_content);
        Picasso.with(this).load(extras.getString("imagenMuseo")).into(header);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textViewPhone = (TextView) findViewById(R.id.textview_telefono);
        textViewPhone.setText(extras.getString("telefonoMuseo"));

        textViewDescripcion = (TextView) findViewById(R.id.textview_descripcion);
        textViewDescripcion.setText(extras.getString("descripcionCortaMuseo"));

        buttonPhone = (Button) findViewById(R.id.button_phone);
        buttonPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Llamada a museo", Snackbar.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_CALL);
                String tel = extras.getString("telefonoMuseo");
                intent.setData(Uri.parse("tel:" + tel));
                startActivity(intent);
            }
        });

        buttonMap = (Button) findViewById(R.id.button_location);
        buttonMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(detailActivity.this, locationActivity.class);
                intent.putExtra("latitud",extras.getString("latitud"));
                intent.putExtra("longitud",extras.getString("longitud"));
                intent.putExtra("nombre",extras.getString("nombreMuseo"));
                startActivity(intent);
                Snackbar.make(v, "Trazar ruta de Museo ", Snackbar.LENGTH_SHORT).show();
            }
        });

        buttonWeb = (Button) findViewById(R.id.button_web);
        buttonWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Snackbar.make(v, "Abrir web de museo: ", Snackbar.LENGTH_SHORT).show();
                Intent intent = new Intent(detailActivity.this, webActivity.class);
                intent.putExtra("url",extras.getString("webMuseo"));
                startActivity(intent);
            }
        });

        buttonFacebook = (Button) findViewById(R.id.button_facebook);
        buttonFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Snackbar.make(v, "Abrir Facebook de Museo", Snackbar.LENGTH_SHORT).show();
                Intent facebookAppIntent;
                try {
                    facebookAppIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(extras.getString("facebookIdMuseo")));
                    startActivity(facebookAppIntent);
                } catch (ActivityNotFoundException e) {
                    facebookAppIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(extras.getString("facebookMuseo")));
                    startActivity(facebookAppIntent);
                }
            }
        });


        buttonTwitterInsta = (Button) findViewById(R.id.twitterinsta);
        //System.out.println("Twitter: "+extras.getString("twitterMuseo")+" Instagram: "+extras.getString("instagramMuseo"));
        if(extras.getString("instagramMuseo").equals("") && extras.getString("twitterMuseo").equals(""))
        {
           buttonTwitterInsta.setVisibility(View.GONE);
            buttonTwitterInsta.setTag(0);
        }else if (extras.getString("twitterMuseo").equals("") && !extras.getString("instagramMuseo").equals(""))
        {
            buttonTwitterInsta.setBackgroundResource(R.mipmap.instagram);
            buttonTwitterInsta.setTag(1);
        }else if (extras.getString("instagramMuseo").equals("") && !extras.getString("twitterMuseo").equals(""))
        {
            buttonTwitterInsta.setBackgroundResource(R.mipmap.twitter);
            buttonTwitterInsta.setTag(2);
        }else if(!extras.getString("instagramMuseo").equals("") && !extras.getString("twitterMuseo").equals(""))
        {
            buttonTwitterInsta.setBackgroundResource(R.mipmap.instagram);
            buttonTwitterInsta.setTag(1);
        }

        buttonTwitterInsta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(detailActivity.this,buttonTwitterInsta.getTag().toString(),Toast.LENGTH_SHORT).show();
                switch(buttonTwitterInsta.getTag().toString())
                {
                    case "1":
                                break;

                    case "2":
                            Intent intent = null;
                             try {
                                // get the Twitter app if possible
                                getApplicationContext().getPackageManager().getPackageInfo("com.twitter.android", 0);
                                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(extras.getString("twitterId")));
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                             } catch (Exception e) {
                                // no Twitter app, revert to browser
                                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(extras.getString("twitterMuseo")));
                            }
                            getApplicationContext().startActivity(intent);
                            break;

                    default: break;
                }
            }
        });

    }

}
