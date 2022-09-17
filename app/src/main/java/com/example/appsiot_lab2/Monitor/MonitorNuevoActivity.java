package com.example.appsiot_lab2.Monitor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.appsiot_lab2.Computadora.ComputadoraNuevoActivity;
import com.example.appsiot_lab2.Lab2Application;
import com.example.appsiot_lab2.R;
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
                Spinner pcActivo = findViewById(R.id.spinner_activo_nmonitor);
                Spinner marca = findViewById(R.id.spinner_marca_nmonitor);
                Spinner pulgadas = findViewById(R.id.spinner_pulgadas_nmonitor);
                EditText anio = findViewById(R.id.et_anio_nmonitor);
                EditText modelo = findViewById(R.id.et_modelo_nmonitor);
                //TODO: Validaciones, activo no repetido, etc
                String activoStr = activo.getText().toString();
                String pcActivoStr = pcActivo.getSelectedItem().toString();
                String marcaStr = marca.getSelectedItem().toString();
                String pulgadasStr = pulgadas.getSelectedItem().toString();
                String anioStr = anio.getText().toString();
                String modeloStr = modelo.getText().toString();
                ArrayList<Monitor> monitorList = ((Lab2Application) MonitorNuevoActivity.this.getApplication()).getMonitorList();
//                for (int i = 0; i < monitorList.size() ; i++) {
//                    if(monitorList.get(i)){
//                        activoNoRepeat = false;
//                        break;
//                    }
//                }
//                if(!activoStr.isEmpty() || !marcaStr.isEmpty() || !anioStr.isEmpty() || !cpuStr.isEmpty()){
//                    if(activoNoRepeat){
//                        computadora.setActivo(activoStr);
//                        computadora.setMarca(marcaStr);
//                        computadora.setAnio(anioStr);
//                        computadora.setCpu(cpuStr);
//                        computadoraList.add(computadora);
//                        ((Lab2Application) ComputadoraNuevoActivity.this.getApplication()).setComputadoraList(computadoraList);
//                    }else{
//                        Toast.makeText(ComputadoraNuevoActivity.this,"No puede repetir el nombre de activo",Toast.LENGTH_LONG).show();
//                    }
//                }else{
//                    Toast.makeText(ComputadoraNuevoActivity.this,"Debe rellenar todos los campos para agregar una computadora",Toast.LENGTH_LONG).show();
//                }
//                finish();
//                return true;
            default:
                return super.onContextItemSelected(item);
//        }

        }
    }

}