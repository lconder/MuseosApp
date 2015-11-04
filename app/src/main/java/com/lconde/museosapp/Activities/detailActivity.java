package com.lconde.museosapp.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.lconde.museosapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class detailActivity extends AppCompatActivity
{

    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbar;
    FloatingActionButton fab;
    Button buttonPhone;
    Button buttonWeb;
    Button buttonFacebook;
    Button buttonTwitter;
    Button buttonInstagram;
    TextView textViewPhone;
    TextView textViewDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        System.out.println("colores: " + R.attr.colorPrimary + "y:" + R.attr.colorPrimaryDark);
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
                Snackbar.make(v, "Compartir Fotografía...", Snackbar.LENGTH_SHORT).show();
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
            public void onClick(View v)
            {
                Snackbar.make(v, "Llamada a museo", Snackbar.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_CALL);
                String tel = extras.getString("telefonoMuseo");
                intent.setData(Uri.parse("tel:" + tel));
                startActivity(intent);
            }
        });

        buttonWeb = (Button) findViewById(R.id.button_web);
        buttonWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Snackbar.make(v, "Abrir web de museo", Snackbar.LENGTH_SHORT).show();
            }
        });

        buttonFacebook = (Button) findViewById(R.id.button_facebook);
        buttonFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Snackbar.make(v, "Abrir Facebook de Museo", Snackbar.LENGTH_SHORT).show();
            }
        });

    }



}
