package com.example.appsiot_lab2;

import android.app.Application;

import com.example.appsiot_lab2.entity.Computadora;

import java.util.ArrayList;

public class Lab2Application extends Application {
    private ArrayList<Computadora> computadoraList = new ArrayList<>();

    public ArrayList<Computadora> getComputadoraList() {
        return computadoraList;
    }

    public void setComputadoraList(ArrayList<Computadora> computadoraList) {
        this.computadoraList = computadoraList;
    }
}
