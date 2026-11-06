package com.proyecto_programadores.DAO;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.proyecto_programadores.ConexionDB4o;
import com.proyecto_programadores.entity.Programador;

public class ProgramadorDAO {
    ConexionDB4o conexionDB4o;
    public void altaProgramador(Programador programador){
        conexionDB4o = new ConexionDB4o();
        ObjectContainer bd =conexionDB4o.abrirConexion();
        bd.store(programador);
        bd.commit();
        bd.close();

    }
    public Programador comprobarUsuario(Programador programador){
        conexionDB4o = new ConexionDB4o();
        ObjectContainer bd =conexionDB4o.abrirConexion();
       Programador p = buscarAtributo(programador,bd);
       bd.close();
       return p;
    }
    private Programador buscarAtributo(Programador programador, ObjectContainer bd) {
        ObjectSet<Programador> todosProgramadores = bd.queryByExample(programador);
        Programador p = null;
        if(todosProgramadores.hasNext()){
            p = todosProgramadores.next();
        }
        return p;
    }
}
