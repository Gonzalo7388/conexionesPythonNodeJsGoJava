package com.apps.mongodemo.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field; 

import java.util.List; 
@Document(collection = "estudiantes")
public class Estudiante {

    @Id
    private String id;

    private String nombre;
    private int edad;
    private String carrera;
    private int semestre;
    private boolean matriculado;
    private Contacto contacto; 
    @Field("cursos_actuales")
    private List<Curso> cursosActuales; 
    // Constructor
    public Estudiante(String nombre, int edad, String carrera, int semestre, boolean matriculado, Contacto contacto, List<Curso> cursosActuales) {
        this.nombre = nombre;
        this.edad = edad;
        this.carrera = carrera;
        this.semestre = semestre;
        this.matriculado = matriculado;
        this.contacto = contacto;
        this.cursosActuales = cursosActuales;
    }
    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }
    public String getCarrera() { return carrera; }
    public void setCarrera(String carrera) { this.carrera = carrera; }
    public int getSemestre() { return semestre; }
    public void setSemestre(int semestre) { this.semestre = semestre; }
    public boolean isMatriculado() { return matriculado; }
    public void setMatriculado(boolean matriculado) { this.matriculado = matriculado; }
    public Contacto getContacto() { return contacto; }
    public void setContacto(Contacto contacto) { this.contacto = contacto; }
    public List<Curso> getCursosActuales() { return cursosActuales; }
    public void setCursosActuales(List<Curso> cursosActuales) { this.cursosActuales = cursosActuales; }

    @Override
    public String toString() {
        return "Estudiante{" +
               "id='" + id + '\'' +
               ", nombre='" + nombre + '\'' +
               ", edad=" + edad +
               ", carrera='" + carrera + '\'' +
               ", semestre=" + semestre +
               ", matriculado=" + matriculado +
               ", contacto=" + contacto +
               ", cursosActuales=" + cursosActuales +
               '}';
    }
}