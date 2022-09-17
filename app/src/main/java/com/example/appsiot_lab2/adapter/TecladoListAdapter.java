package com.example.appsiot_lab2.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.appsiot_lab2.R;
import com.example.appsiot_lab2.entity.Computadora;
import com.example.appsiot_lab2.entity.Teclado;

import java.util.ArrayList;

public class TecladoListAdapter extends ArrayAdapter<Teclado> {

    private int resource;
    private Context context;

    public TecladoListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Teclado> objects){
        super(context, resource, objects);
        this.resource=resource;
        this.context = context;
    }


    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Teclado teclado = getItem(position);
        convertView = LayoutInflater.from(context).inflate(resource, parent, false);
        ((TextView) convertView.findViewById(R.id.textViewComputadoraItem)).setText(teclado.getInfo());

        return convertView;
    }

}
