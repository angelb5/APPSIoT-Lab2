package com.example.appsiot_lab2.Computadora;

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
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appsiot_lab2.Lab2Application;
import com.example.appsiot_lab2.Monitor.MonitorActualizarActivity;
import com.example.appsiot_lab2.Monitor.MonitorListActivity;
import com.example.appsiot_lab2.R;
import com.example.appsiot_lab2.adapter.MonitorListAdapter;
import com.example.appsiot_lab2.entity.Computadora;
import com.example.appsiot_lab2.entity.Monitor;

import java.util.ArrayList;
import java.util.Optional;

public class ComputadoraActualizarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computadora_actualizar);
        setTitle("Actualizar");
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
                TextView activoTextView = findViewById(R.id.tv_activo_compu_act);
                EditText anioEditText = findViewById(R.id.et_anio_compu_act);
                EditText cpuEditText = findViewById(R.id.et_cpu_compu_act);
                Spinner marcaSpinner = findViewById(R.id.sp_marca_compu_act);;
                //TODO: Validaciones, activo no repetido, etc
                String activo = activoTextView.getText().toString();
                String anio = anioEditText.getText().toString();
                String marca = marcaSpinner.getSelectedItem().toString();
                String cpuStr = cpuEditText.getText().toString();
                cpuStr = cpuStr.trim();
                ArrayList<Computadora> computadoraList = ((Lab2Application) ComputadoraActualizarActivity.this.getApplication()).getComputadoraList();

                Log.d("msg", String.valueOf(!activo.isEmpty()));
                if(!activo.isEmpty() && !marca.isEmpty() && !anio.isEmpty() && !cpuStr.isEmpty() && !(marcaSpinner.getSelectedItemPosition()==0)){
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
            case R.id.btnDelete:

                AlertDialog.Builder alerta = new AlertDialog.Builder(ComputadoraActualizarActivity.this);
                alerta.setMessage("¿Esta seguro que desea borrar?");

                alerta.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    public void onClick(DialogInterface dialog, int whichButton) {
                        ArrayList<Computadora> computadoraListDel = ((Lab2Application) ComputadoraActualizarActivity.this.getApplication()).getComputadoraList();
                        TextView textViewdel = findViewById(R.id.tv_activo_compu_act);
                        String activodel = textViewdel.getText().toString();

                        for(Computadora computadora : computadoraListDel){
                            String activoobt = computadora.getActivo();
                            if(activodel.equals(activoobt)){
                                computadoraListDel.remove(computadora);
                                ((Lab2Application) ComputadoraActualizarActivity.this.getApplication()).setComputadoraList(computadoraListDel);
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