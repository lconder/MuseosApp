package com.lconde.museosapp.Activities;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.lconde.museosapp.R;

import java.io.UnsupportedEncodingException;

public class ManualActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{

    Spinner spinner;
    int position;
    String selection;
    Button buttonAcceder;
    SharedPreferences sp;
    EditText editTextId;
    NfcAdapter nfcAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual);

        sp = getSharedPreferences("datos", Context.MODE_PRIVATE);


        if(sp.getBoolean("bandera",false))
        {
            Intent intent = new Intent(getApplicationContext(),ProfileActivity.class);
            startActivity(intent);
        }
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.universidades,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        editTextId = (EditText) findViewById(R.id.editText_id);
        buttonAcceder = (Button) findViewById(R.id.button_acceder);

        buttonAcceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("universidad", selection);
                editor.putString("id", editTextId.getText().toString());
                editor.putBoolean("bandera", true);
                editor.commit();
                finish();
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(intent);

            }
        });
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        this.position = position;
        selection = parent.getItemAtPosition(position).toString();
        //Toast.makeText(this,"Selección actual: "+selection,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }




}
