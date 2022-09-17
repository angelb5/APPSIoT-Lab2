package com.example.appsiot_lab2.Monitor;

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
import com.example.appsiot_lab2.adapter.MonitorListAdapter;
import com.example.appsiot_lab2.entity.Monitor;

import java.util.ArrayList;
import java.util.Optional;


public class MonitorListActivity extends AppCompatActivity {

    ListView monitorlistView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor_list);
        setTitle("Monitor");
        monitorlistView = (ListView) findViewById(R.id.monitorListView);
        mostrarLista();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        ((TextView) findViewById(R.id.monitorEmpty)).setText("No hay monitores ingresados");
        mostrarLista();
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        TextView monitorEmpty = findViewById(R.id.monitorEmpty);
        monitorEmpty.setTypeface(null, Typeface.ITALIC);
        ((ListView) findViewById(R.id.monitorListView)).setEmptyView(monitorEmpty);
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
                ((TextView) findViewById(R.id.monitorEmpty)).setText("No hay monitores ingresados");
                mostrarLista();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    public void irNuevo(View view){
        Intent intent = new Intent(this, MonitorNuevoActivity.class);
        startActivity(intent);
    }

    public void mostrarLista(){
        ArrayList<Monitor> monitorList = ((Lab2Application) this.getApplication()).getMonitorList();
        MonitorListAdapter monitorListAdapter = new MonitorListAdapter(MonitorListActivity.this, R.layout.item_equipos,monitorList);
        monitorlistView.setAdapter(monitorListAdapter);
        monitorlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MonitorListActivity.this, MonitorActualizarActivity.class);
                intent.putExtra("monitor",monitorList.get(i));
                startActivity(intent);
            }
        });
    }

    public void mostrarAlerta(){
        AlertDialog.Builder alerta = new AlertDialog.Builder(MonitorListActivity.this);
        alerta.setTitle("Monitor");

        final EditText busquedaEditText = new EditText(MonitorListActivity.this);
        alerta.setView(busquedaEditText);
        alerta.setPositiveButton("Buscar", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            public void onClick(DialogInterface dialog, int whichButton) {
                String busqueda = busquedaEditText.getText().toString();
                ArrayList<Monitor> monitorList = ((Lab2Application) MonitorListActivity.this.getApplication()).getMonitorList();
                Optional<Monitor> optMonitor = monitorList.stream().filter(c -> c.getActivo().equals(busqueda)).findAny();
                ArrayList<Monitor> monitorBusquedaList = new ArrayList<>();
                if(optMonitor.isPresent()){
                    monitorBusquedaList.add(optMonitor.get());
                }else{
                    ((TextView) findViewById(R.id.monitorEmpty)).setText("No existe el equipo con Activo: "+busqueda);
                }
                MonitorListAdapter monitorBusquedaListAdapter = new MonitorListAdapter(MonitorListActivity.this, R.layout.item_equipos,monitorBusquedaList);
                monitorlistView.setAdapter(monitorBusquedaListAdapter);
                monitorlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(MonitorListActivity.this, MonitorActualizarActivity.class);
                        intent.putExtra("monitor",monitorBusquedaList.get(i));
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