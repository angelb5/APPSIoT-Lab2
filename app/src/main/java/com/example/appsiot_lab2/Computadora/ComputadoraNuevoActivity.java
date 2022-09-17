package com.example.appsiot_lab2.Computadora;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.appsiot_lab2.Lab2Application;
import com.example.appsiot_lab2.R;
import com.example.appsiot_lab2.entity.Computadora;

import java.util.ArrayList;
import java.util.Random;

public class ComputadoraNuevoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computadora_nuevo);
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
                Random random = new Random();
                ArrayList<Computadora> testList = new ArrayList<>();
                testList.add(new Computadora("C1210","Dell","2023","Ryzen 7000"));
                testList.add(new Computadora("C1211","Hewlet Packard","2032","Ryzen 9000"));
                testList.add(new Computadora("C1212","tochiba","1940","Ryzen -1"));
                testList.add(new Computadora("C1213","Hell","2022","intel i7"));
                testList.add(new Computadora("C1214","no","4000","q"));
                testList.add(new Computadora("C1215","a","2022","intel i"));
                testList.add(new Computadora("C1216","b","2022","intel j"));
                testList.add(new Computadora("C1217","c","2022","intellij"));
                testList.add(new Computadora("C1218","d","2022","amd amd amd"));
                //Fin de datos de prueba
                Computadora nuevaComputadora = testList.get(random.nextInt(testList.size()));
                //TODO: Validaciones, activo no repetido, etc

                ArrayList<Computadora> computadoraList = ((Lab2Application) ComputadoraNuevoActivity.this.getApplication()).getComputadoraList();
                computadoraList.add(nuevaComputadora);
                ((Lab2Application) ComputadoraNuevoActivity.this.getApplication()).setComputadoraList(computadoraList);
                finish();
                return true;
            default:
                return super.onContextItemSelected(item);
        }

    }


}