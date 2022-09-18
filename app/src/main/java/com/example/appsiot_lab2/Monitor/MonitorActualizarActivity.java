package com.example.appsiot_lab2.Monitor;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appsiot_lab2.Computadora.ComputadoraActualizarActivity;
import com.example.appsiot_lab2.Lab2Application;
import com.example.appsiot_lab2.R;
import com.example.appsiot_lab2.Teclado.TecladoActualizarActivity;
import com.example.appsiot_lab2.entity.Computadora;
import com.example.appsiot_lab2.entity.Monitor;
import com.example.appsiot_lab2.entity.Teclado;

import java.util.ArrayList;
import java.util.List;

public class MonitorActualizarActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor_actualizar);

        Intent intent = getIntent();
        Monitor monitor = (Monitor) intent.getSerializableExtra("monitor");

        TextView activo = findViewById(R.id.tv_monitor_act);
        Spinner pcSpinner = findViewById(R.id.spinner_activo_monitor);
        Spinner marcaSpinner = findViewById(R.id.spinner_marca_monitor);
        Spinner pulgadasSpinner = findViewById(R.id.spinner_pulgadas_monitor);
        EditText anio = findViewById(R.id.et_anio);
        EditText modelo = findViewById(R.id.plaintextModeloact);

        List<String> activos = ((Lab2Application) MonitorActualizarActivity.this.getApplication()).getPCActivos();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(MonitorActualizarActivity.this, android.R.layout.simple_spinner_item,activos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pcSpinner.setAdapter(adapter);

        activo.setText(monitor.getActivo());
        anio.setText(monitor.getAnio());
        modelo.setText(monitor.getModelo());
        String pcCompare = monitor.getPc();
        String marcaCompare = monitor.getMarca();
        String pulgadasCompare = monitor.getPulgadas();

        for(int i = 0; i < pcSpinner.getCount(); i++){
            if(pcSpinner.getItemAtPosition(i).toString().equals(pcCompare)){
                pcSpinner.setSelection(i);
                break;
            }
        }

        for(int i = 0; i < marcaSpinner.getCount(); i++){
            if(marcaSpinner.getItemAtPosition(i).toString().equals(marcaCompare)){
                marcaSpinner.setSelection(i);
                break;
            }
        }

        for(int i = 0; i < pulgadasSpinner.getCount(); i++){
            if(pulgadasSpinner.getItemAtPosition(i).toString().equals(pulgadasCompare)){
                pulgadasSpinner.setSelection(i);
                break;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actualizar,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.btnActualizar:
                TextView activo = findViewById(R.id.tv_monitor_act);
                Spinner pc = findViewById(R.id.spinner_activo_monitor);
                Spinner marca = findViewById(R.id.spinner_marca_monitor);
                Spinner pulgadas = findViewById(R.id.spinner_pulgadas_monitor);
                EditText anio = findViewById(R.id.et_anio);
                EditText modelo = findViewById(R.id.plaintextModeloact);
                //TODO: Validaciones, activo no repetido, etc
                String activoStr = activo.getText().toString();
                String pcStr = pc.getSelectedItem().toString();
                String marcaStr = marca.getSelectedItem().toString();
                String pulgadasStr = pulgadas.getSelectedItem().toString();
                String anioStr = anio.getText().toString();
                String modeloStr = modelo.getText().toString();
                modeloStr = modeloStr.trim();
                ArrayList<Monitor> monitorList = ((Lab2Application) MonitorActualizarActivity.this.getApplication()).getMonitorList();

                Log.d("msg", String.valueOf(!activoStr.isEmpty()));
                if(!anioStr.isEmpty() && !modeloStr.isEmpty() && !pcStr.isEmpty() && !marcaStr.isEmpty() && !pulgadasStr.isEmpty()
                        && !(marca.getSelectedItemPosition()==0) && !(pulgadas.getSelectedItemPosition()==0) && !(pc.getSelectedItemPosition()==0)){
                    for(Monitor monitor : monitorList){
                        String activoobt = monitor.getActivo();
                        if(activoStr.equals(activoobt)){
                            monitor.setPc(pcStr);
                            monitor.setMarca(marcaStr);
                            monitor.setAnio(anioStr);
                            monitor.setPulgadas(pulgadasStr);
                            monitor.setModelo(modeloStr);
                            ((Lab2Application) MonitorActualizarActivity.this.getApplication()).setMonitorList(monitorList);
                            finish();
                        }
                    }
                }else{
                    Toast.makeText(MonitorActualizarActivity.this,"Debe rellenar todos los campos para actualizar una computadora",Toast.LENGTH_LONG).show();
                }
                return true;

            case R.id.btnDelete:

                AlertDialog.Builder alerta = new AlertDialog.Builder(MonitorActualizarActivity.this);
                alerta.setMessage("¿Esta seguro que desea borrar?");

                alerta.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    public void onClick(DialogInterface dialog, int whichButton) {
                        ArrayList<Monitor> monitorListDel = ((Lab2Application) MonitorActualizarActivity.this.getApplication()).getMonitorList();
                        TextView textViewdel = findViewById(R.id.tv_monitor_act);
                        String activodel = textViewdel.getText().toString();

                        for(Monitor monitor : monitorListDel){
                            String activoobt = monitor.getActivo();
                            if(activodel.equals(activoobt)){
                                monitorListDel.remove(monitor);
                                ((Lab2Application) MonitorActualizarActivity.this.getApplication()).setMonitorList(monitorListDel);
                                finish();
                                break;
                            }

                        }
                    }
                });
                alerta.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                });
                alerta.show();

            default:
                return super.onContextItemSelected(item);
        }

    }

}