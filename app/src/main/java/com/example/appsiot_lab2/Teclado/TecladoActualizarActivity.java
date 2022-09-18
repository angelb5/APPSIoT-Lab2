package com.example.appsiot_lab2.Teclado;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appsiot_lab2.Computadora.ComputadoraActualizarActivity;
import com.example.appsiot_lab2.Lab2Application;
import com.example.appsiot_lab2.Monitor.MonitorActualizarActivity;
import com.example.appsiot_lab2.R;
import com.example.appsiot_lab2.entity.Computadora;
import com.example.appsiot_lab2.entity.Monitor;
import com.example.appsiot_lab2.entity.Teclado;

import java.util.ArrayList;

public class TecladoActualizarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teclado_actualizar);

        TextView activo = findViewById(R.id.textViewActivoTeclado);
        Spinner pcActivo = findViewById(R.id.spinnerPc);
        Spinner marca = findViewById(R.id.spinnerMarca);
        Spinner idioma = findViewById(R.id.spinnerIdioma);
        EditText anio = findViewById(R.id.editTextTextAnioAct);
        EditText modelo = findViewById(R.id.editTextTextModeloAct);

        Intent intent = getIntent();
        Teclado teclado = (Teclado) intent.getSerializableExtra("teclado");

        activo.setText(teclado.getActivo());

        anio.setText(teclado.getAnio());
        modelo.setText(teclado.getModelo());

        String pcCompare = teclado.getPc();
        String marcaCompare = teclado.getMarca();
        String idiomaCompare = teclado.getModelo();
        for (int i = 0; i < pcActivo.getCount(); i++) {
            if (pcActivo.getItemAtPosition(i).toString().equals(pcCompare)) {
                pcActivo.setSelection(i);
                break;
            }
        }

        for (int i = 0; i < marca.getCount(); i++) {
            if (marca.getItemAtPosition(i).toString().equals(marcaCompare)) {
                marca.setSelection(i);
                break;
            }
        }

        for (int i = 0; i < idioma.getCount(); i++) {
            if (idioma.getItemAtPosition(i).toString().equals(idiomaCompare)) {
                idioma.setSelection(i);
                break;
            }
        }

    }


    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actualizar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.btnActualizar:
                TextView activo = findViewById(R.id.textViewActivoTeclado);
                Spinner pcActivo = findViewById(R.id.spinnerPc);
                Spinner marca = findViewById(R.id.spinnerMarca);
                Spinner idioma = findViewById(R.id.spinnerIdioma);
                EditText anio = findViewById(R.id.editTextTextAnioAct);
                EditText modelo = findViewById(R.id.editTextTextModeloAct);
                //TODO: Validaciones, activo no repetido, etc
                String activoStr = activo.getText().toString();
                String pcStr = pcActivo.getSelectedItem().toString();
                String marcaStr = marca.getSelectedItem().toString();
                String idiomaStr = idioma.getSelectedItem().toString();
                String anioStr = anio.getText().toString();
                String modeloStr = modelo.getText().toString();
                modeloStr = modeloStr.trim();
                ArrayList<Teclado> tecladoList = ((Lab2Application) TecladoActualizarActivity.this.getApplication()).getTecladoList();

                Log.d("msg", String.valueOf(!activoStr.isEmpty()));
                if(!anioStr.isEmpty() && !modeloStr.isEmpty() && !idiomaStr.isEmpty() && !marcaStr.isEmpty() && !pcStr.isEmpty() && !(marca.getSelectedItemPosition()==0)){
                    for(Teclado teclado : tecladoList){
                        String activoobt = teclado.getActivo();
                        if(activoStr.equals(activoobt)){
                            teclado.setActivo(activoStr);
                            teclado.setMarca(marcaStr);
                            teclado.setPc(pcStr);
                            teclado.setAnio(anioStr);
                            teclado.setModelo(modeloStr);
                            tecladoList.add(teclado);;
                            ((Lab2Application) TecladoActualizarActivity.this.getApplication()).setTecladoList(tecladoList);
                            finish();
                        }
                    }
                }else{
                    Toast.makeText(TecladoActualizarActivity.this,"Debe rellenar todos los campos para actualizar una computadora",Toast.LENGTH_LONG).show();
                }
                return true;

            case R.id.btnDelete:

                AlertDialog.Builder alerta = new AlertDialog.Builder(TecladoActualizarActivity.this);
                alerta.setMessage("¿Esta seguro que desea borrar?");

                alerta.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    public void onClick(DialogInterface dialog, int whichButton) {
                        ArrayList<Teclado> tecladoListDel = ((Lab2Application) TecladoActualizarActivity.this.getApplication()).getTecladoList();
                        TextView textViewdel = findViewById(R.id.textViewActivoTeclado);
                        String activodel = textViewdel.getText().toString();

                        for(Teclado teclado : tecladoListDel){
                            String activoobt = teclado.getActivo();
                            if(activodel.equals(activoobt)){
                                tecladoListDel.remove(teclado);
                                ((Lab2Application) TecladoActualizarActivity.this.getApplication()).setTecladoList(tecladoListDel);
                                finish();
                                break;
                            }

                        }
                    }
                });
                alerta.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                });
                alerta.show();


            default:
                return super.onContextItemSelected(item);
        }

    }
}