package com.example.firebaseejemplo;

/**
 * Created by Pablo on 18/8/2017.
 */

public class Persona {

    String nombre;
    String apellido;
    String ci;

    public Persona() {
    }

    public Persona(String apellido,String ci , String nombre) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.ci = ci;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }
}
