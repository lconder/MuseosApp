package com.lconde.museosapp.Activities;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.lconde.museosapp.R;

import java.io.UnsupportedEncodingException;

public class IdentifyActivity extends AppCompatActivity
{
    Button openQR;
    Button openManual;
    SharedPreferences sp;
    NfcAdapter adapter;
    PendingIntent pendingIntent;
    IntentFilter writeTagFilters[];
    Tag myTag;
    boolean writeMode;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify);

        adapter = NfcAdapter.getDefaultAdapter(this);
        pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        IntentFilter tagDetected = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
        writeTagFilters = new IntentFilter[]{tagDetected};

        sp = getSharedPreferences("datos", Context.MODE_PRIVATE);
        if(sp.getBoolean("bandera",false))
        {
            Intent intent = new Intent(getApplicationContext(),ProfileActivity.class);
            startActivity(intent);
        }

        openQR = (Button) findViewById(R.id.button_QR);
        openManual = (Button) findViewById(R.id.button_manual);

        openQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), QRreaderActivity.class);
                startActivity(intent);
            }
        });

        openManual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ManualActivity.class);
                startActivity(intent);
            }
        });
    }

    public void onPause(){
        super.onPause();
        WriteModeOff();
    }
    public void onResume(){
        super.onResume();
        WriteModeOn();
    }

    @SuppressLint("NewApi") protected void onNewIntent(Intent intent){
        if(NfcAdapter.ACTION_TAG_DISCOVERED.equals(intent.getAction())){
            myTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            Toast.makeText(this, "ok detected" , Toast.LENGTH_LONG).show();
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("universidad", "buap");
            editor.putString("id", "12345");
            editor.putBoolean("bandera", true);
            editor.commit();
            finish();
            Intent intent1 = new Intent(getApplicationContext(), ProfileActivity.class);
            startActivity(intent1);

        }
    }

    @SuppressLint("NewApi") private void WriteModeOn(){
        writeMode = true;
        adapter.enableForegroundDispatch(this, pendingIntent, writeTagFilters, null);
    }

    @SuppressLint("NewApi") private void WriteModeOff(){
        writeMode = false;
        adapter.disableForegroundDispatch(this);
    }


}