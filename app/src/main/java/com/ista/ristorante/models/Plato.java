package com.ista.ristorante.models;

public class Plato {

    private int codigo;
    private String nombre;
    private String tipo;
    private String ingredientes;
    private String costo;
    private String pvp;

    public Plato(String nombre, String tipo, String ingredientes, String costo, String pvp) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.ingredientes = ingredientes;
        this.costo = costo;
        this.pvp = pvp;
    }

    public Plato(int codigo, String nombre, String tipo, String ingredientes, String costo, String pvp) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.tipo = tipo;
        this.ingredientes = ingredientes;
        this.costo = costo;
        this.pvp = pvp;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getCosto() {
        return costo;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }

    public String getPvp() {
        return pvp;
    }

    public void setPvp(String pvp) {
        this.pvp = pvp;
    }

    @Override
    public String toString() {
        return "nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", pvp='" + pvp + '\'';
    }
}
