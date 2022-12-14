package com.example.appsiot_lab2.entity;

import java.io.Serializable;

public class Teclado implements Serializable {
    private String activo;
    private String pc;
    private String marca;
    private String idioma;
    private String anio;
    private String modelo;

    public Teclado() {
    }

    public Teclado(String activo, String pc, String marca, String idioma, String anio, String modelo) {
        this.activo = activo;
        this.pc = pc;
        this.marca = marca;
        this.idioma = idioma;
        this.anio = anio;
        this.modelo = modelo;
    }

    public String getInfo(){
        return "Activo: "+activo+"\nPC: "+pc+"\nMarca: "+marca+"\nAño: "+anio+"\nIdioma: "+idioma+"\nModelo: "+modelo;
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

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }
}
