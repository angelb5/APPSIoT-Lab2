package com.example.appsiot_lab2.entity;

import java.io.Serializable;

public class Monitor implements Serializable {
    private String activo;
    private String pc;
    private String marca;
    private String pulgadas;
    private String anio;
    private String modelo;

    public Monitor(String activo, String pc, String marca, String pulgadas, String anio, String modelo) {
        this.activo = activo;
        this.pc = pc;
        this.marca = marca;
        this.pulgadas = pulgadas;
        this.anio = anio;
        this.modelo = modelo;
    }

    public Monitor() {
    }

    public String getInfo(){
        return "Activo: "+activo+"\nPC: "+pc+"\nMarca: "+marca+"\nAño: "+anio+"\nModelo: "+modelo;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public String getPc() {
        return pc;
    }

    public void setPc(String pc) {
        this.pc = pc;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPulgadas() {
        return pulgadas;
    }

    public void setPulgadas(String pulgadas) {
        this.pulgadas = pulgadas;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

}
