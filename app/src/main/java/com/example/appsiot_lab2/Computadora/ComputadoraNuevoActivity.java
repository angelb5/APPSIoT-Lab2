package com.example.appsiot_lab2.Computadora;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.appsiot_lab2.Lab2Application;
import com.example.appsiot_lab2.R;
import com.example.appsiot_lab2.entity.Computadora;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ComputadoraNuevoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computadora_nuevo);
        setTitle("Nuevo");
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.menu_nuevo,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.btnBarAdd:
                //Datos de prueba
                Computadora computadora = new Computadora();
                Boolean activoNoRepeat = true;
                EditText activo = findViewById(R.id.et_activo_ncompu);
                Spinner marca = findViewById(R.id.spinner_marca_ncomputadora);
                EditText anio = findViewById(R.id.et_anio_ncompu);
                EditText cpu = findViewById(R.id.et_cpu_ncompu);
                //TODO: Validaciones, activo no repetido, etc
                String activoStr = activo.getText().toString();
                activoStr = activoStr.trim();
                String marcaStr = marca.getSelectedItem().toString();
                String anioStr = anio.getText().toString();
                String cpuStr = cpu.getText().toString();
                cpuStr = cpuStr.trim();
                ArrayList<Computadora> computadoraList = ((Lab2Application) ComputadoraNuevoActivity.this.getApplication()).getComputadoraList();
                for (int i = 0; i < computadoraList.size() ; i++) {
                    if(computadoraList.get(i).getActivo().equals(activoStr)){
                        activoNoRepeat = false;
                        break;
                    }
                }
                Log.d("msg", String.valueOf(!activoStr.isEmpty()));
                if(!activoStr.isEmpty() && !marcaStr.isEmpty() && !anioStr.isEmpty() && !cpuStr.isEmpty() && !(marca.getSelectedItemPosition()==0)){
                    if(activoNoRepeat){
                        computadora.setActivo(activoStr);
                        computadora.setMarca(marcaStr);
                        computadora.setAnio(anioStr);
                        computadora.setCpu(cpuStr);
                        computadoraList.add(computadora);
                        ((Lab2Application) ComputadoraNuevoActivity.this.getApplication()).setComputadoraList(computadoraList);
                        finish();
                    }else{
                        Toast.makeText(ComputadoraNuevoActivity.this,"No puede repetir el nombre de activo",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(ComputadoraNuevoActivity.this,"Debe rellenar todos los campos para agregar una computadora",Toast.LENGTH_LONG).show();
                }
                return true;
            default:
                return super.onContextItemSelected(item);
        }

    }


}