package com.example.appsiot_lab2.Computadora;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

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

        int index = 0;
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

    public void obtenidos (View view){

        TextView textView = findViewById(R.id.tv_activo_compu_act);
        EditText editText = findViewById(R.id.et_anio_compu_act);
        EditText editText1 = findViewById(R.id.et_cpu_compu_act);

        String activo = textView.getText().toString();
        String anio = editText.getText().toString();
        String cpu = editText1.getText().toString();

        ArrayList<Computadora> computadoraList = ((Lab2Application) ComputadoraActualizarActivity.this.getApplication()).getComputadoraList();
        for(Computadora computadora : computadoraList){
            String activoobt = computadora.getActivo();
            if(activo.equals(activoobt)){
                computadora.setAnio(anio);
                computadora.setCpu(cpu);
            }

        }
    }

}