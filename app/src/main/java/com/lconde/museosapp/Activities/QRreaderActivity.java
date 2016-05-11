package com.lconde.museosapp.Activities;

import android.content.Intent;
import android.graphics.PointF;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.dlazaro66.qrcodereaderview.QRCodeReaderView;
import com.lconde.museosapp.Classes.Pieza;
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

public class QRreaderActivity extends AppCompatActivity implements QRCodeReaderView.OnQRCodeReadListener
{
    private QRCodeReaderView mydecoderview;
    TextView tvStatus;
    private List<Pieza> piezas;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrreader);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        piezas =  new ArrayList<>();

        try {
            readJson();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mydecoderview = (QRCodeReaderView) findViewById(R.id.qrdecoderview);
        mydecoderview.setOnQRCodeReadListener(this);

        tvStatus = (TextView) findViewById(R.id.textViewStatus);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (Build.VERSION.SDK_INT < 19)
        {
            FrameLayout statusBar = (FrameLayout) findViewById(R.id.statusBar);
            statusBar.setVisibility(View.GONE);
        }

    }

    @Override
    public void onQRCodeRead(String text, PointF[] points)
    {
        tvStatus.setText(text);
        String codeQR = text;
        if(codeQR.indexOf(getResources().getString(R.string.serverP)) != -1)
        {
            //System.out.println("Codigo sin URL:" + codeQR.replaceAll(getResources().getString(R.string.serverP), ""));
            busqueda(Integer.parseInt(codeQR.replaceAll(getResources().getString(R.string.serverP),"")));

        } else {
            tvStatus.setText("Escanea un código QR de Museos App");
        }

    }

    @Override
    public void cameraNotFound()
    {
        tvStatus.setText("No se encuentra la cámara");


    }

    @Override
    public void QRCodeNotFoundOnCamImage() {
        tvStatus.setText("No se detecta ningún QR");

    }

    @Override
    protected void onResume() {
        super.onResume();
        mydecoderview.getCameraManager().startPreview();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mydecoderview.getCameraManager().stopPreview();
    }

    public void readJson() throws IOException, JSONException
    {
        System.out.println("Read Json");
        File sdcard = Environment.getExternalStorageDirectory();
        File file = new File(sdcard,"cacheP.json");
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
            JSONObject jsonObject =jsonArray.getJSONObject(i);
            String nombre = jsonObject.getString("nombre");
            String categoria= jsonObject.getString("categoria");
            int id = jsonObject.getInt("id_pieza");
            String autor = jsonObject.getString("autor");
            String anio = jsonObject.getString("ano");
            String descripcion = jsonObject.getString("descripcion");
            String imagen = jsonObject.getString("imagen");
            Pieza temp =  new Pieza(nombre, categoria,id, autor, anio,descripcion,imagen);
            piezas.add(temp);
            temp.toString();
            temp = null;
        }
    }

    public void busqueda(int id)
    {
        for(Pieza i: piezas)
        {
            if(i.searchId(id))
            {

                Intent intent = new Intent(QRreaderActivity.this, PieceActivity.class);
                intent.putExtra("nombre", i.getNombre());
                intent.putExtra("descripcion",i.getDescripcion());
                intent.putExtra("imagen",i.getImagen());
                intent.putExtra("anio",i.getAnio());
                intent.putExtra("autor",i.getAutor());
                startActivity(intent);
            }
        }
    }
}
