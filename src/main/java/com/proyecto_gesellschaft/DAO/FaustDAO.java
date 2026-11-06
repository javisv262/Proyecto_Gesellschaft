package com.proyecto_gesellschaft.DAO;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.proyecto_gesellschaft.ConexionDB4o;
import com.proyecto_gesellschaft.entity.Faust;

public class FaustDAO {
    ConexionDB4o conexionDB4o;
    public void altaFaust(Faust faust){
        conexionDB4o = new ConexionDB4o();
        ObjectContainer bd =conexionDB4o.abrirConexion();
        bd.store(faust);
        bd.commit();
        bd.close();

    }
    public Faust comprobarUsuario(Faust faust){
        conexionDB4o = new ConexionDB4o();
        ObjectContainer bd =conexionDB4o.abrirConexion();
       Faust p = buscarAtributo(faust,bd);
       bd.close();
       return p;
    }
    private Faust buscarAtributo(Faust faust, ObjectContainer bd) {
        ObjectSet<Faust> todosFaust = bd.queryByExample(faust);
        Faust p = null;
        if(todosFaust.hasNext()){
            p = todosFaust.next();
        }
        return p;
    }
}
