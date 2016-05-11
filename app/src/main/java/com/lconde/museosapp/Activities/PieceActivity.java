package com.lconde.museosapp.Activities;

import android.os.Build;
import android.os.Environment;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.lconde.museosapp.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;

public class PieceActivity extends AppCompatActivity implements TextToSpeech.OnInitListener
{
    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbar;
    TextToSpeech tts;
    Locale spanish;
    String text;
    String server;
    Button buttonSpeak;
    ImageView imageViewPiece;
    TextView textViewAutor;
    TextView textViewAnio;
    TextView textViewDescripcion;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piece);

        final Bundle extras = getIntent().getExtras();

        Toast.makeText(PieceActivity.this,extras.getString("URL"),Toast.LENGTH_SHORT).show();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(extras.getString("nombre"));
        collapsingToolbar.setContentScrimColor(R.color.md_pink_700);

        imageViewPiece = (ImageView) findViewById(R.id.img_content);
        Picasso.with(getApplicationContext()).load(extras.getString("imagen")).into(imageViewPiece);

        textViewAutor = (TextView) findViewById(R.id.textview_autor);

        textViewAnio = (TextView) findViewById(R.id.textview_anio);
        textViewDescripcion = (TextView) findViewById(R.id.textview_descripcion);

       /* fab=(FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Compartir Fotografía...", Snackbar.LENGTH_SHORT).show();
            }
        });*/

        textViewAutor.setText(extras.getString("autor"));
        textViewAnio.setText(extras.getString("anio"));
        text = extras.getString("descripcion");
        textViewDescripcion.setText(extras.getString("descripcion"));

        buttonSpeak = (Button) findViewById(R.id.button_play);
        buttonSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOut();
            }
        });

        tts = new TextToSpeech(this, this);
        spanish = new Locale("es", "ES");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public void onInit(int status)
    {
        if(status == TextToSpeech.SUCCESS)
        {
            int result = tts.setLanguage(spanish);

            if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED)
            {
                Log.e("TTS","This Language is not supported");
            }else{
                System.out.println("Funcionando");
            }
        }else{
            Log.e("TTS","Initializaiton Failed!");
        }

    }

    @Override
    public void onDestroy()
    {
        if( tts != null)
        {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    private void speakOut()
    {
        if (Build.VERSION.SDK_INT >= 21)
        {
            tts.speak(text, TextToSpeech.QUEUE_FLUSH,null, null);
            Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
        }else{
            tts.speak(text, TextToSpeech.QUEUE_FLUSH,null);
            Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
        }

    }
}
