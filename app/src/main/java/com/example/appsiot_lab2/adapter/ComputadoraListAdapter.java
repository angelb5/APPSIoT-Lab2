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

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ComputadoraListAdapter extends ArrayAdapter<Computadora> {

    private int resource;
    private Context context;

    public ComputadoraListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Computadora> objects) {
        super(context, resource, objects);
        this.resource=resource;
        this.context = context;
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Computadora computadora = getItem(position);
        convertView = LayoutInflater.from(context).inflate(resource, parent, false);
        ((TextView) convertView.findViewById(R.id.textViewComputadoraItem)).setText(computadora.getInfo());

        return convertView;
    }
}
