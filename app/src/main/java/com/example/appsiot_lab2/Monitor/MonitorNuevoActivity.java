package com.example.appsiot_lab2.Monitor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.appsiot_lab2.Computadora.ComputadoraListaActivity;
import com.example.appsiot_lab2.Lab2Application;
import com.example.appsiot_lab2.R;
import com.example.appsiot_lab2.Teclado.TecladoNuevoActivity;
import com.example.appsiot_lab2.entity.Computadora;
import com.example.appsiot_lab2.entity.Monitor;

import java.util.ArrayList;

public class MonitorNuevoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor_nuevo);
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.menu_nuevo,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btnBarAdd:
                //Datos de prueba
                Monitor monitor = new Monitor();
                Boolean activoNoRepeat = true;
                EditText activo = findViewById(R.id.et_activo_nmonitor);
                ArrayList<Computadora> computadoraList = ((Lab2Application) MonitorNuevoActivity.this.getApplication()).getComputadoraList();
                ArrayAdapter<Computadora> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,computadoraList);
                Spinner pcActivo = findViewById(R.id.spinner_activo_nmonitor);
                pcActivo.setAdapter(adapter);
                Spinner marca = findViewById(R.id.spinner_marca_nmonitor);
                Spinner pulgadas = findViewById(R.id.spinner_pulgadas_nmonitor);
                EditText anio = findViewById(R.id.et_anio_nmonitor);
                EditText modelo = findViewById(R.id.et_modelo_nteclado);
                //TODO: Validaciones, activo no repetido, etc
                String activoStr = activo.getText().toString();
                Computadora pcActivoCom = (Computadora) pcActivo.getSelectedItem();
                String marcaStr = marca.getSelectedItem().toString();
                String pulgadasStr = pulgadas.getSelectedItem().toString();
                String anioStr = anio.getText().toString();
                String modeloStr = modelo.getText().toString();
                ArrayList<Monitor> monitorList = ((Lab2Application) MonitorNuevoActivity.this.getApplication()).getMonitorList();
                for (int i = 0; i < monitorList.size() ; i++) {
                    if(monitorList.get(i).getActivo().equals(activoStr)){
                        activoNoRepeat = false;
                        break;
                    }
                }
                if(!activoStr.isEmpty() && !pcActivoCom.getActivo().isEmpty() && !marcaStr.isEmpty() && !pulgadasStr.isEmpty() && !anioStr.isEmpty() && !modeloStr.isEmpty()){
                    if(activoNoRepeat){
                        monitor.setActivo(activoStr);
                        monitor.setPc(pcActivoCom);
                        monitor.setMarca(marcaStr);
                        monitor.setAnio(anioStr);
                        monitor.setPulgadas(pulgadasStr);
                        monitor.setModelo(modeloStr);
                        ((Lab2Application) MonitorNuevoActivity.this.getApplication()).setMonitorList(monitorList);
                        finish();
                    }else{
                        Toast.makeText(MonitorNuevoActivity.this,"No puede repetir el nombre de activo",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(MonitorNuevoActivity.this,"Debe rellenar todos los campos para agregar un monitor",Toast.LENGTH_LONG).show();
                }
                return true;
            default:
                return super.onContextItemSelected(item);
        }

    }

}