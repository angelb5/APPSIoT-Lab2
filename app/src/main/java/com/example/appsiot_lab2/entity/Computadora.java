package com.example.appsiot_lab2.entity;

import java.io.Serializable;

public class Computadora implements Serializable {
    private String activo;
    private String marca;
    private String anio;
    private String cpu;

    public Computadora() {
    }

    public Computadora(String activo, String marca, String anio, String cpu) {
        this.activo = activo;
        this.marca = marca;
        this.anio = anio;
        this.cpu = cpu;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getInfo(){
        return "Activo: "+activo+"\nMarca: "+marca+"\nAño: "+anio+"\nCPU: "+cpu;
    }
}
