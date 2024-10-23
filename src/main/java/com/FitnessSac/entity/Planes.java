package com.FitnessSac.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Planes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_plan;
    private String nombre_plan;
    private String descripcion;
    private int duracion;
    private int cantidad_entrenamientos;
    private String tipo_entrenamiento;
    private double precio;
    private String estado;

    public int getId_plan() {
        return id_plan;
    }

    public void setId_plan(int id_plan) {
        this.id_plan = id_plan;
    }

    public String getNombre_plan() {
        return nombre_plan;
    }

    public void setNombre_plan(String nombre_plan) {
        this.nombre_plan = nombre_plan;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getCantidad_entrenamientos() {
        return cantidad_entrenamientos;
    }

    public void setCantidad_entrenamientos(int cantidad_entrenamientos) {
        this.cantidad_entrenamientos = cantidad_entrenamientos;
    }

    public String getTipo_entrenamiento() {
        return tipo_entrenamiento;
    }

    public void setTipo_entrenamiento(String tipo_entrenamiento) {
        this.tipo_entrenamiento = tipo_entrenamiento;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
