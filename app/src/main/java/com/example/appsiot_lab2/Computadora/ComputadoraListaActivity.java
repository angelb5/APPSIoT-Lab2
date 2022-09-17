package com.example.appsiot_lab2.Computadora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.appsiot_lab2.Lab2Application;
import com.example.appsiot_lab2.R;
import com.example.appsiot_lab2.adapter.ComputadoraListAdapter;
import com.example.appsiot_lab2.entity.Computadora;

import java.util.ArrayList;
import java.util.Random;

public class ComputadoraListaActivity extends AppCompatActivity {

    ListView computadoralistView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computadora_lista);
        setTitle("Computadora");
        computadoralistView = (ListView) findViewById(R.id.computadoraListView);
        mostrarLista();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mostrarLista();
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        TextView computadoraEmpty = findViewById(R.id.computadoraEmpty);
        computadoraEmpty.setTypeface(null, Typeface.ITALIC);
        ((ListView) findViewById(R.id.computadoraListView)).setEmptyView(computadoraEmpty);
    }

    public void irNuevo(View view){
        Intent intent = new Intent(this, ComputadoraNuevoActivity.class);
        startActivity(intent);
    }

    public void mostrarLista(){
        ArrayList<Computadora> computadoraList = ((Lab2Application) this.getApplication()).getComputadoraList();
        ComputadoraListAdapter computadoraListAdapter = new ComputadoraListAdapter(ComputadoraListaActivity.this, R.layout.item_computadora,computadoraList);
        computadoralistView.setAdapter(computadoraListAdapter);
        computadoralistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("msgAS", "La computadora es la numero: "+i);
            }
        });
    }
}