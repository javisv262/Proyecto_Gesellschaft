package com.proyecto_programadores.entity;

public class Programador {
    private String usuario;
    private String password;
    private String genero;
    private String lenguaje;

    public Programador(String usuario, String password, String genero, String lenguaje) {
        this.usuario = usuario;
        this.password = password;
        this.genero = genero;
        this.lenguaje = lenguaje;
    }

    public Programador() {
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

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }

    @Override
    public String toString() {
        return"Usuario: " + this.usuario + " Contraseña: " + this.password + " Genero: " + this.genero + " Lenguaje: " + this.lenguaje;}

}
