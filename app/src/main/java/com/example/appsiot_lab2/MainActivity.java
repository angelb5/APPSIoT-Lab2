package com.example.appsiot_lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.appsiot_lab2.Computadora.ComputadoraListaActivity;
import com.example.appsiot_lab2.Monitor.MonitorListActivity;
import com.example.appsiot_lab2.Teclado.TecladoListActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Equipos");
    }

    public void computadoraList(View view){
        Intent intent = new Intent(this, ComputadoraListaActivity.class);
        startActivity(intent);
    }

    public void tecladoList(View view){
        Intent intent = new Intent(this, TecladoListActivity.class);
        startActivity(intent);

    }

    public void monitorList(View view){
        Intent intent = new Intent(this, MonitorListActivity.class);
        startActivity(intent);

    }

    public void reporte(View view){
        Intent intent = new Intent(this, MonitorListActivity.class);
        startActivity(intent);

    }

}