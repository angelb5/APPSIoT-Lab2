package com.example.appsiot_lab2.Teclado;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.appsiot_lab2.Lab2Application;
import com.example.appsiot_lab2.Monitor.MonitorActualizarActivity;
import com.example.appsiot_lab2.R;
import com.example.appsiot_lab2.entity.Computadora;
import com.example.appsiot_lab2.entity.Teclado;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TecladoNuevoActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teclado_nuevo);
        List<String> activos = ((Lab2Application) TecladoNuevoActivity.this.getApplication()).getPCActivos();
        Spinner pcActivo = findViewById(R.id.spinner_activo_nteclado);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(TecladoNuevoActivity.this, android.R.layout.simple_spinner_item,activos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pcActivo.setAdapter(adapter);
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
                Teclado teclado = new Teclado();
                Boolean activoNoRepeat = true;
                EditText activo = findViewById(R.id.et_activo_nteclado);
                Spinner pcActivo = findViewById(R.id.spinner_activo_nteclado);
                Spinner marca = findViewById(R.id.spinner_marca_nteclado);
                Spinner idioma = findViewById(R.id.spinner_idioma_nteclado);
                EditText anio = findViewById(R.id.et_anio_nteclado);
                EditText modelo = findViewById(R.id.et_modelo_nmonitor);
                //TODO: Validaciones, activo no repetido, etc
                String activoStr = activo.getText().toString();
                activoStr = activoStr.trim();
                String pcActivoStr = pcActivo.getSelectedItem().toString();
                String marcaStr = marca.getSelectedItem().toString();
                String idiomaStr = idioma.getSelectedItem().toString();
                String anioStr = anio.getText().toString();
                String modeloStr = modelo.getText().toString();
                modeloStr = modeloStr.trim();
                ArrayList<Teclado> tecladoList = ((Lab2Application) TecladoNuevoActivity.this.getApplication()).getTecladoList();
                for (int i = 0; i < tecladoList.size() ; i++) {
                    if(tecladoList.get(i).getActivo().equals(activoStr)){
                        activoNoRepeat = false;
                        break;
                    }
                }
                if(!activoStr.isEmpty() && !pcActivoStr.isEmpty() && !marcaStr.isEmpty() && !idiomaStr.isEmpty() && !anioStr.isEmpty() && !modeloStr.isEmpty()
                        && !(marca.getSelectedItemPosition()==0) && !(pcActivo.getSelectedItemPosition()==0)){
                    if(activoNoRepeat){
                        teclado.setActivo(activoStr);
                        teclado.setMarca(marcaStr);
                        teclado.setPc(pcActivoStr);
                        teclado.setAnio(anioStr);
                        teclado.setModelo(modeloStr);
                        teclado.setIdioma(idiomaStr);
                        tecladoList.add(teclado);
                        ((Lab2Application) TecladoNuevoActivity.this.getApplication()).setTecladoList(tecladoList);
                        finish();
                    }else{
                        Toast.makeText(TecladoNuevoActivity.this,"No puede repetir el nombre de activo",Toast.LENGTH_LONG).show();
                    }
                }else{

                    Toast.makeText(TecladoNuevoActivity.this,"Debe rellenar todos los campos para agregar un teclado",Toast.LENGTH_LONG).show();
                }
                return true;
            default:
                return super.onContextItemSelected(item);
        }

    }


}