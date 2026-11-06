package com.proyecto_gesellschaft.entity;

public class Faust {
    private String usuario;
    private String password;
    private String permisos;
    private String lenguaje;

    public Faust(String usuario, String password, String permisos, String lenguaje) {
        this.usuario = usuario;
        this.password = password;
        this.permisos = permisos;
        this.lenguaje = lenguaje;
    }

    public Faust() {
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPermisos() {
        return permisos;
    }

    public void setPermisos(String permisos) {
        this.permisos = permisos;
    }

    public String getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }

    @Override
    public String toString() {
        return"Usuario: " + this.usuario + " Contraseña: " + this.password + " Permisos: " + this.permisos + " Lenguaje: " + this.lenguaje;}

}
