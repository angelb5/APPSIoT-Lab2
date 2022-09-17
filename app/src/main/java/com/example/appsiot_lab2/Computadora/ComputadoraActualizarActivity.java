package com.example.appsiot_lab2.Computadora;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;

import com.example.appsiot_lab2.R;
import com.example.appsiot_lab2.entity.Computadora;

public class ComputadoraActualizarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computadora_actualizar);

        Intent intent = getIntent();
        Computadora computadora = (Computadora) intent.getSerializableExtra("computadora");

        TextView textView = findViewById(R.id.textViewActivocomp);

        EditText editText = findViewById(R.id.plaintextAnioactcompu);
        EditText editText1 = findViewById(R.id.plaintextCpuact);

        textView.setText(computadora.getActivo());
        editText.setText(computadora.getAnio());
        editText1.setText(computadora.getCpu());

    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actualizar,menu);
        return true;
    }



}