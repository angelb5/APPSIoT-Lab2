package com.example.appsiot_lab2.Computadora;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.appsiot_lab2.Lab2Application;
import com.example.appsiot_lab2.R;
import com.example.appsiot_lab2.adapter.ComputadoraListAdapter;
import com.example.appsiot_lab2.entity.Computadora;

import java.util.ArrayList;
import java.util.Optional;
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
        ((TextView) findViewById(R.id.computadoraEmpty)).setText("No hay computadoras ingresadas");
        mostrarLista();
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        TextView computadoraEmpty = findViewById(R.id.computadoraEmpty);
        computadoraEmpty.setTypeface(null, Typeface.ITALIC);
        ((ListView) findViewById(R.id.computadoraListView)).setEmptyView(computadoraEmpty);
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lista,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.itemListaBuscar:
                mostrarAlerta();
                return true;
            case R.id.itemListaTodo:
                ((TextView) findViewById(R.id.computadoraEmpty)).setText("No hay computadoras ingresadas");
                mostrarLista();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
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

    public void mostrarAlerta(){
        AlertDialog.Builder alerta = new AlertDialog.Builder(ComputadoraListaActivity.this);
        alerta.setTitle("Manual Item Search");
        alerta.setMessage("Input Search Query");

        final EditText busquedaEditText = new EditText(ComputadoraListaActivity.this);
        alerta.setView(busquedaEditText);
        alerta.setPositiveButton("Buscar", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            public void onClick(DialogInterface dialog, int whichButton) {
                String busqueda = busquedaEditText.getText().toString();
                ArrayList<Computadora> computadoraList = ((Lab2Application) ComputadoraListaActivity.this.getApplication()).getComputadoraList();
                Optional<Computadora> optComputadora = computadoraList.stream().filter(c -> c.getActivo().equals(busqueda)).findAny();
                ArrayList<Computadora> computadoraBusquedaList = new ArrayList<>();
                if(optComputadora.isPresent()){
                    computadoraBusquedaList.add(optComputadora.get());
                }else{
                    ((TextView) findViewById(R.id.computadoraEmpty)).setText("No existe el equipo con Activo: "+busqueda);
                }
                ComputadoraListAdapter computadoraBusquedaListAdapter = new ComputadoraListAdapter(ComputadoraListaActivity.this, R.layout.item_computadora,computadoraBusquedaList);
                computadoralistView.setAdapter(computadoraBusquedaListAdapter);
                computadoralistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Log.d("msgAS", "La computadora es la numero: "+i);
                    }
                });
            }
        });
        alerta.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });
        alerta.show();
    }
}