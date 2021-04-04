/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import excepciones.AlumnoExistenteException;
import excepciones.ArchivoInvalidoException;
import excepciones.CargarArchivoException;
import excepciones.GuardarArchivoException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Alumno;

/**
 *
 * @author DELL
 */
public class Controller implements Serializable {

    private ArrayList<Alumno> lista;

    public Controller() throws CargarArchivoException, ArchivoInvalidoException {
        cargar();
    }

    public Boolean add(Alumno alumno) throws AlumnoExistenteException {
        if (lista.contains(alumno)) {
            throw new AlumnoExistenteException("El numero de control ya ha sido asignado");
        } else {
            return lista.add(alumno);
        }
    }

    public Integer catidadAlumnos() {
        return lista.size();
    }

    public Alumno getAlumno(int index) {
        return lista.get(index);
    }

    public ArrayList<Alumno> getAlumno() {
        return lista;
    }

//    public void eliminarAlumno(String alum) {
//        int i=0;
//        for (Alumno alumno : lista) {
//            if(alumno.getNoControl().equals(alum)){
//                lista.remove(i);
//                
//            }
//            i++;
//        }
//    }
    public Boolean eliminarAlumno(Alumno alum) {
        if(lista.contains(alum)){
            return lista.remove(alum);            
        }else{
            return false;
        }
    }

    public Alumno buscar(String noControl) {
        for (Alumno alumno : lista) {
            if (alumno.getNoControl().equals(noControl)) {
                return alumno;
            }
        }
        return null;
    }

    public void guardar() throws GuardarArchivoException {
        try {
            File file = new File("alumnos.dat");
            FileOutputStream output = new FileOutputStream(file);
            ObjectOutputStream writer = new ObjectOutputStream(output);

            writer.writeObject(lista);

            writer.close();
            output.close();
        } catch (IOException ex) {
            throw new GuardarArchivoException("Hubo un error en disco");
        }
    }

    private void cargar() throws CargarArchivoException, ArchivoInvalidoException {
        File file = new File("alumnos.dat");
        if (file.exists()) {
            try {
                FileInputStream input = new FileInputStream(file);
                ObjectInputStream reader = new ObjectInputStream(input);

                lista = (ArrayList<Alumno>) reader.readObject();

                reader.close();
                input.close();
            } catch (IOException ex) {
                throw new CargarArchivoException("Error al cargar el archivo");
            } catch (ClassNotFoundException ex) {
                throw new ArchivoInvalidoException("Archivo de origen ha sido corrompido");
            }
        }else{
            lista = new ArrayList<>();
        }

    }

}
