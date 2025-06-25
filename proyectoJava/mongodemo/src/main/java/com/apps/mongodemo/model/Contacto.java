package com.apps.mongodemo.model;

public class Contacto {
    private String email;
    private String telefono;

    // Constructor
    public Contacto(String email, String telefono) {
        this.email = email;
        this.telefono = telefono;
    }

    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Contacto{" +
               "email='" + email + '\'' +
               ", telefono='" + telefono + '\'' +
               '}';
    }
}