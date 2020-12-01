package com.example.jsonreader.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.jsonreader.R;
import com.example.jsonreader.modelos.Lugar;

import java.util.ArrayList;
import java.util.List;

public class LugarAdaptador extends ArrayAdapter {

    public LugarAdaptador(@NonNull Context context, @NonNull ArrayList<Lugar> lugares) {
        super(context, 0, lugares);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Lugar lugar = (Lugar) getItem(position);
        if(convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.rowlugar, parent, false);
        }
        TextView nombre = convertView.findViewById(R.id.txt_nombre);
        TextView tipo = convertView.findViewById(R.id.txt_tipo);
        TextView descripcion = convertView.findViewById(R.id.txt_descripcion);
        TextView municipio = convertView.findViewById(R.id.txt_municipio);
        TextView direccion = convertView.findViewById(R.id.txt_direccion);
        TextView telefono = convertView.findViewById(R.id.txt_telefono);

        nombre.setText(lugar.getNombre());
        tipo.setText(lugar.getTipo());
        descripcion.setText(lugar.getDescripcion());
        municipio.setText(lugar.getMunicipio());
        direccion.setText(lugar.getDireccion());
        telefono.setText(lugar.getTelefono());

        return convertView;
    }
}
