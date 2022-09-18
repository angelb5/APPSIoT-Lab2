package com.example.appsiot_lab2.Teclado;

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
import android.widget.Toast;

import com.example.appsiot_lab2.Lab2Application;
import com.example.appsiot_lab2.Monitor.MonitorListActivity;
import com.example.appsiot_lab2.R;
import com.example.appsiot_lab2.adapter.TecladoListAdapter;
import com.example.appsiot_lab2.entity.Teclado;

import java.util.ArrayList;
import java.util.Optional;

public class TecladoListActivity extends AppCompatActivity {

    ListView tecladolistView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teclado_list);
        setTitle("Teclado");
        tecladolistView = (ListView) findViewById(R.id.tecladoListView);
        mostrarLista();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        ((TextView) findViewById(R.id.tecladoEmpty)).setText("No hay teclados ingresados");
        mostrarLista();
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        TextView tecladoEmpty = findViewById(R.id.tecladoEmpty);
        tecladoEmpty.setTypeface(null, Typeface.ITALIC);
        ((ListView) findViewById(R.id.tecladoListView)).setEmptyView(tecladoEmpty);
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
                ((TextView) findViewById(R.id.tecladoEmpty)).setText("No hay teclados ingresados");
                mostrarLista();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    public void irNuevo(View view){
        if(((Lab2Application) TecladoListActivity.this.getApplication()).getComputadoraList().size()==0){
            Toast.makeText(TecladoListActivity.this, "Aun no hay Computadoras registradas", Toast.LENGTH_SHORT).show();
        }else{
            Intent intent = new Intent(this, TecladoNuevoActivity.class);
            startActivity(intent);
        }
    }

    public void mostrarLista(){
        ArrayList<Teclado> tecladoList = ((Lab2Application) this.getApplication()).getTecladoList();
        TecladoListAdapter tecladoListAdapter = new TecladoListAdapter(TecladoListActivity.this, R.layout.item_equipos,tecladoList);
        tecladolistView.setAdapter(tecladoListAdapter);
        tecladolistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(TecladoListActivity.this, TecladoActualizarActivity.class);
                intent.putExtra("teclado",tecladoList.get(i));
                startActivity(intent);
            }
        });
    }

    public void mostrarAlerta(){
        AlertDialog.Builder alerta = new AlertDialog.Builder(TecladoListActivity.this);
        alerta.setTitle("Teclado");

        final EditText busquedaEditText = new EditText(TecladoListActivity.this);
        alerta.setView(busquedaEditText);
        alerta.setPositiveButton("Buscar", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            public void onClick(DialogInterface dialog, int whichButton) {
                String busqueda = busquedaEditText.getText().toString();
                ArrayList<Teclado> tecladoList = ((Lab2Application) TecladoListActivity.this.getApplication()).getTecladoList();
                Optional<Teclado> optTeclado = tecladoList.stream().filter(c -> c.getActivo().equals(busqueda)).findAny();
                ArrayList<Teclado> tecladoBusquedaList = new ArrayList<>();
                if(optTeclado.isPresent()){
                    tecladoBusquedaList.add(optTeclado.get());
                }else{
                    ((TextView) findViewById(R.id.tecladoEmpty)).setText("No existe el equipo con Activo: "+busqueda);
                }
                TecladoListAdapter tecladoBusquedaListAdapter = new TecladoListAdapter(TecladoListActivity.this, R.layout.item_equipos,tecladoBusquedaList);
                tecladolistView.setAdapter(tecladoBusquedaListAdapter);
                tecladolistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(TecladoListActivity.this, TecladoActualizarActivity.class);
                        intent.putExtra("teclado",tecladoBusquedaList.get(i));
                        startActivity(intent);
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