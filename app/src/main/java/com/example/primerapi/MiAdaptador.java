package com.example.primerapi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MiAdaptador extends RecyclerView.Adapter<MiAdaptador.MiViewHolder> {
    ArrayList<Farmacia>lista;


    public MiAdaptador(ArrayList<Farmacia> lista) {
        this.lista = lista;
    }
    public static class MiViewHolder extends RecyclerView.ViewHolder{
        TextView txtnombre;
        ImageView img;

        public MiViewHolder(@NonNull View itemView) {
            super(itemView);
            txtnombre = itemView.findViewById(R.id.txtNombre);
            img = itemView.findViewById(R.id.imageView);
        }
    }
    @NonNull
    @Override
    public MiAdaptador.MiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflador = LayoutInflater.from(parent.getContext());
        View v = inflador.inflate(R.layout.elemento,parent,false);

        MiViewHolder mvh = new MiViewHolder(v);

        return mvh;
    }
    @Override
    public void onBindViewHolder(@NonNull MiAdaptador.MiViewHolder holder, int position) {

        holder.txtnombre.setText(""+lista.get(position).getNombre());
        if (lista.get(position).getTurno().equals("14-B")){
            holder.img.setImageDrawable(holder.itemView.getResources().getDrawable(R.mipmap.ic_verde));
        }


    }
    @Override
    public int getItemCount() {
        return lista.size();
    }
}


