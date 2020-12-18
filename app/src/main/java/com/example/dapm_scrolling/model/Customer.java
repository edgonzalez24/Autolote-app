package com.example.dapm_scrolling.model;

public class Customer {
    //atributos
    private int idvehicle;
    private String marca;
    private String modelo;
    private String color;
    private String anio;
    private String tipo;

    //constructor con parámetros
    public Customer(int idvehicle, String marca, String modelo, String color, String anio, String tipo) {
        this.idvehicle = idvehicle;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.anio = anio;
        this.tipo = tipo;
    }

    //constructor vacío
    public Customer() {
    }

    //getter and setter

    public int getIdvehicle() {
        return idvehicle;
    }

    public void setIdvehicle(int idvehicle) {
        this.idvehicle = idvehicle;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca= marca;
    }

    public String getModelo(){
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio= anio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
