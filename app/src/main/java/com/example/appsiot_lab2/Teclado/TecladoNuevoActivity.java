package com.example.appsiot_lab2.Teclado;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.appsiot_lab2.Lab2Application;
import com.example.appsiot_lab2.R;
import com.example.appsiot_lab2.entity.Computadora;
import com.example.appsiot_lab2.entity.Teclado;

import java.util.ArrayList;

public class TecladoNuevoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teclado_nuevo);
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
                Teclado teclado = new Teclado();
                Boolean activoNoRepeat = true;
                EditText activo = findViewById(R.id.et_activo_nteclado);
                ArrayList<Computadora> computadoraList = ((Lab2Application) TecladoNuevoActivity.this.getApplication()).getComputadoraList();
                ArrayAdapter<Computadora> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,computadoraList);
                Spinner pcActivo = findViewById(R.id.spinner_activo_nteclado);
                pcActivo.setAdapter(adapter);
                Spinner marca = findViewById(R.id.spinner_marca_nteclado);
                Spinner idioma = findViewById(R.id.spinner_idioma_nteclado);
                EditText anio = findViewById(R.id.et_anio_nteclado);
                EditText modelo = findViewById(R.id.et_modelo_nteclado);
                //TODO: Validaciones, activo no repetido, etc
                String activoStr = activo.getText().toString();
                Computadora pcActivoCom = (Computadora) pcActivo.getSelectedItem();
                String marcaStr = marca.getSelectedItem().toString();
                String idiomaStr = idioma.getSelectedItem().toString();
                String anioStr = anio.getText().toString();
                String modeloStr = modelo.getText().toString();
                ArrayList<Teclado> tecladoList = ((Lab2Application) TecladoNuevoActivity.this.getApplication()).getTecladoList();
                for (int i = 0; i < tecladoList.size() ; i++) {
                    if(tecladoList.get(i).getActivo().equals(activoStr)){
                        activoNoRepeat = false;
                        break;
                    }
                }
                if(!activoStr.isEmpty() && !pcActivoCom.getActivo().isEmpty() && !marcaStr.isEmpty() && !idiomaStr.isEmpty() && !anioStr.isEmpty() && !modeloStr.isEmpty()){
                    if(activoNoRepeat){
                        teclado.setActivo(activoStr);
                        teclado.setMarca(marcaStr);
                        teclado.setPc(pcActivoCom);
                        teclado.setAnio(anioStr);
                        teclado.setModelo(modeloStr);
                        tecladoList.add(teclado);
                        ((Lab2Application) TecladoNuevoActivity.this.getApplication()).setComputadoraList(computadoraList);
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