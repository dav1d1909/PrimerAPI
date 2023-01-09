package com.example.primerapi;

import android.util.JsonReader;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class Peticion extends Thread{
    ArrayList<Farmacia> lista;
    public Peticion(ArrayList<Farmacia>lista){
        this.lista = lista;
    }
    @Override
    public void run() {
        try {
            URL url = new URL("https://www.zaragoza.es/sede/servicio/farmacia?rf=html&srsname=wgs84&tipo=guardia&start=0&rows=50&distance=500\n");

            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

            conn.setRequestProperty("Accept","application/geo+json");

            if (conn.getResponseCode() == 200){
                InputStream is = conn.getInputStream();
                InputStreamReader isr = new InputStreamReader(is,"UTF-8");

                JsonReader jr = new JsonReader(isr);


                jr.beginObject();
                String clave = null;
                String clave2 = null;
                String clave3 = null;
                String cadena = null;

                while (jr.hasNext()){
                    clave = jr.nextName();
                    if (clave.equals("features")){
                        jr.beginArray();
                        while (jr.hasNext()){
                            jr.beginObject();
                            Farmacia f = new Farmacia();
                            while(jr.hasNext()){
                                clave2 = jr.nextName();
                                if (clave2.equals("properties")){
                                    jr.beginObject();
                                    while(jr.hasNext()){
                                        clave3 = jr.nextName();
                                        if (clave3.equals("title")){
                                            f.setNombre(jr.nextString());
                                        } else if (clave3.equals("guardia")){
                                                jr.beginObject();
                                                while (jr.hasNext()){
                                                cadena= jr.nextName();
                                                if (cadena.equals("fecha")){
                                                    f.setFecha(jr.nextString());
                                                }else if(cadena.equals("turno")){
                                                    f.setTurno(jr.nextString());
                                                }else if(cadena.equals("horario")){
                                                    f.setHorario(jr.nextString());
                                                }else if(cadena.equals("sector")){
                                                    f.setSector(jr.nextString());
                                                }else {
                                                    jr.skipValue();
                                                }
                                                }
                                                jr.endObject();
                                        }else{
                                            jr.skipValue();
                                        }
                                    }
                                    jr.endObject();
                                }else {
                                    jr.skipValue();
                                }
                            }
                            this.lista.add(f);

                            jr.endObject();
                        }
                        jr.endArray();
                    }else {
                        jr.skipValue();
                    }
                }

                jr.endObject();
            }
        }catch(MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
