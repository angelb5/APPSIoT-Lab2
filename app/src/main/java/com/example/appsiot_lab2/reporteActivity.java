package com.example.appsiot_lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.appsiot_lab2.entity.Computadora;
import com.example.appsiot_lab2.entity.Monitor;
import com.example.appsiot_lab2.entity.Teclado;

import java.util.ArrayList;

public class reporteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte);
        setTitle("Reporte");

        TextView reporte = findViewById(R.id.tvReporte);

        ArrayList<Computadora> computadoraList = ((Lab2Application) this.getApplication()).getComputadoraList();
        ArrayList<Monitor> monitorList = ((Lab2Application) this.getApplication()).getMonitorList();
        ArrayList<Teclado> tecladoList = ((Lab2Application) this.getApplication()).getTecladoList();

        int cant2022 = 0;

        for(Computadora computadora : computadoraList) {
            if(computadora.getAnio().equals("2022")){
                cant2022++;
            }
        }

        reporte.setText("Computadoras:\n-Total:"+computadoraList.size()+"\n-Del año 2022:"+cant2022+"\n\nTeclado:"+tecladoList.size()+"\nMonitor:"+monitorList.size());



    }
}