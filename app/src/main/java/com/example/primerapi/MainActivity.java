package com.example.primerapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Array;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Farmacia> lista = new ArrayList<>();

        Peticion p = new Peticion(lista);
        p.start();

        RecyclerView rv = findViewById(R.id.listaFarmacias);
        rv.setHasFixedSize(true);

        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);

        try {
            p.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        MiAdaptador adaptador = new MiAdaptador(lista);
        rv.setAdapter(adaptador);


    }
}