package com.proyecto_gesellschaft.DAO;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.proyecto_gesellschaft.ConexionDB4o;
import com.proyecto_gesellschaft.entity.Faust;

import java.util.ArrayList;
import java.util.List;

public class FaustDAO {
    ConexionDB4o conexionDB4o;
    public void altaFaust(Faust faust){
        conexionDB4o = new ConexionDB4o();
        ObjectContainer bd =conexionDB4o.abrirConexion();
        bd.store(faust);
        bd.commit();
        bd.close();

    }
    public void editarFaust(Faust faust){
        conexionDB4o = new ConexionDB4o();
        ObjectContainer bd =conexionDB4o.abrirConexion();

        Faust filtro = new Faust();
        filtro.setUsuario(faust.getUsuario());

        Faust fguardada = buscarAtributo(filtro,bd);
        fguardada.setPassword(faust.getPassword());
        fguardada.setPermisos(faust.getPermisos());
        fguardada.setLenguaje(faust.getLenguaje());

        bd.store(fguardada);
        bd.commit();
        bd.close();
    }
    public List<Faust> listarFausts(String filtro, Faust excluir){
        List<Faust> lista = new ArrayList<Faust>();
        conexionDB4o = new ConexionDB4o();
        ObjectContainer bd =conexionDB4o.abrirConexion();
        try {
            if (!filtro.equals("Todos")) {
                Faust ejemplo = new Faust();
                ejemplo.setLenguaje(filtro);
                ObjectSet<Faust> resultado = bd.queryByExample(ejemplo);
                while (resultado.hasNext()) {
                    lista.add(resultado.next());
                }
            }else {
                ObjectSet<Faust> resultado = bd.queryByExample(Faust.class);
                while (resultado.hasNext()) {
                    Faust f = resultado.next();
                    if(!f.getUsuario().equals(excluir.getUsuario())){
                        lista.add(f);
                    }
                }
            }
        }finally {
            bd.close();
        }
        return lista;
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
