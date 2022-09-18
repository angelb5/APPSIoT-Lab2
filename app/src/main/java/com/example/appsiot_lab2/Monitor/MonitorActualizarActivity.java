package com.example.appsiot_lab2.Monitor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appsiot_lab2.Computadora.ComputadoraActualizarActivity;
import com.example.appsiot_lab2.Lab2Application;
import com.example.appsiot_lab2.R;
import com.example.appsiot_lab2.entity.Computadora;
import com.example.appsiot_lab2.entity.Monitor;

import java.util.ArrayList;

public class MonitorActualizarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor_actualizar);

        Intent intent = getIntent();
        Monitor monitor = (Monitor) intent.getSerializableExtra("monitor");

        TextView activo = findViewById(R.id.tv_monitor_act);
        Spinner pc = findViewById(R.id.spinner_activo_monitor);
        Spinner marca = findViewById(R.id.spinner_marca_monitor);
        Spinner pulgadas = findViewById(R.id.spinner_pulgadas_monitor);
        EditText anio = findViewById(R.id.et_anio);
        EditText modelo = findViewById(R.id.plaintextModeloact);




        activo.setText(monitor.getActivo());
        anio.setText(monitor.getAnio());
        modelo.setText(monitor.getModelo());
        String pcCompare = monitor.getPc();
        String marcaCompare = monitor.getMarca();
        String pulgadasCompare = monitor.getModelo();

        for(int i = 0; i < pc.getCount(); i++){
            if(pc.getItemAtPosition(i).toString().equals(pcCompare)){
                pc.setSelection(i);
                break;
            }
        }

        for(int i = 0; i < marca.getCount(); i++){
            if(marca.getItemAtPosition(i).toString().equals(marcaCompare)){
                marca.setSelection(i);
                break;
            }
        }

        for(int i = 0; i < pulgadas.getCount(); i++){
            if(pulgadas.getItemAtPosition(i).toString().equals(pulgadasCompare)){
                pulgadas.setSelection(i);
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
                //Datos de prueba


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
                if(!anioStr.isEmpty() && !modeloStr.isEmpty() && !pcStr.isEmpty() && !marcaStr.isEmpty() && !pulgadasStr.isEmpty()){
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
            default:
                return super.onContextItemSelected(item);
        }

    }

}