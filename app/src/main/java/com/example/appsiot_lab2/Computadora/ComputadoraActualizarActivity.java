package com.example.appsiot_lab2.Computadora;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appsiot_lab2.Lab2Application;
import com.example.appsiot_lab2.R;
import com.example.appsiot_lab2.entity.Computadora;

import java.util.ArrayList;

public class ComputadoraActualizarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computadora_actualizar);

        Intent intent = getIntent();
        Computadora computadora = (Computadora) intent.getSerializableExtra("computadora");

        TextView textView = findViewById(R.id.tv_activo_compu_act);
        Spinner spinner = findViewById(R.id.sp_marca_compu_act);
        EditText editText = findViewById(R.id.et_anio_compu_act);
        EditText editText1 = findViewById(R.id.et_cpu_compu_act);

        String compareValue = computadora.getMarca();

        textView.setText(computadora.getActivo());
        editText.setText(computadora.getAnio());
        editText1.setText(computadora.getCpu());

        for(int i = 0; i < spinner.getCount(); i++){
            if(spinner.getItemAtPosition(i).toString().equals(compareValue)){
                spinner.setSelection(i);
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


                TextView textView = findViewById(R.id.tv_activo_compu_act);
                EditText editText = findViewById(R.id.et_anio_compu_act);
                EditText editText1 = findViewById(R.id.et_cpu_compu_act);
                Spinner spinner = findViewById(R.id.sp_marca_compu_act);;
                //TODO: Validaciones, activo no repetido, etc


                String activo = textView.getText().toString();
                String anio = editText.getText().toString();
                String marca = spinner.getOnItemSelectedListener().toString();
                String cpuStr = editText1.getText().toString();
                cpuStr = cpuStr.trim();
                ArrayList<Computadora> computadoraList = ((Lab2Application) ComputadoraActualizarActivity.this.getApplication()).getComputadoraList();

                Log.d("msg", String.valueOf(!activo.isEmpty()));
                if(!activo.isEmpty() && !marca.isEmpty() && !anio.isEmpty() && !cpuStr.isEmpty()){
                    for(Computadora computadora : computadoraList){
                        String activoobt = computadora.getActivo();
                        if(activo.equals(activoobt)){
                            computadora.setMarca(marca);
                            computadora.setAnio(anio);
                            computadora.setCpu(cpuStr);
                            ((Lab2Application) ComputadoraActualizarActivity.this.getApplication()).setComputadoraList(computadoraList);
                            finish();
                        }

                    }

                }else{
                    Toast.makeText(ComputadoraActualizarActivity.this,"Debe rellenar todos los campos para actualizar una computadora",Toast.LENGTH_LONG).show();
                }
                return true;
            default:
                return super.onContextItemSelected(item);
        }

    }

}