package com.example.appsiot_lab2;

import android.app.Application;

import com.example.appsiot_lab2.entity.Computadora;
import com.example.appsiot_lab2.entity.Monitor;
import com.example.appsiot_lab2.entity.Teclado;

import java.util.ArrayList;

public class Lab2Application extends Application {
    private ArrayList<Computadora> computadoraList = new ArrayList<>();
    private ArrayList<Monitor> monitorList = new ArrayList<>();
    private ArrayList<Teclado> tecladoList = new ArrayList<>();

    public ArrayList<Monitor> getMonitorList() {
        return monitorList;
    }

    public void setMonitorList(ArrayList<Monitor> monitorList) {
        this.monitorList = monitorList;
    }

    public ArrayList<Teclado> getTecladoList() {
        return tecladoList;
    }

    public void setTecladoList(ArrayList<Teclado> tecladoList) {
        this.tecladoList = tecladoList;
    }

    public ArrayList<Computadora> getComputadoraList() {
        return computadoraList;
    }

    public void setComputadoraList(ArrayList<Computadora> computadoraList) {
        this.computadoraList = computadoraList;
    }
}
