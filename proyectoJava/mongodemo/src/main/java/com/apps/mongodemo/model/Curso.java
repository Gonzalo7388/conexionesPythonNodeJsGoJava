package com.apps.mongodemo.model;

import org.springframework.data.mongodb.core.mapping.Field;

public class Curso {
    @Field("nombre_curso") 
    private String nombreCurso;
    private int creditos;

    // Constructor
    public Curso(String nombreCurso, int creditos) {
        this.nombreCurso = nombreCurso;
        this.creditos = creditos;
    }

    // Getters and Setters 
    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    @Override
    public String toString() {
        return "Curso{" +
               "nombreCurso='" + nombreCurso + '\'' +
               ", creditos=" + creditos +
               '}';
    }
}