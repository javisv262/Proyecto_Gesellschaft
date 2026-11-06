package com.proyecto_gesellschaft;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

public class ConexionDB4o {
    static final String DB_FILE = "gesellschaft.db4o";

    public ObjectContainer abrirConexion(){

        return Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), DB_FILE);
    }
}
