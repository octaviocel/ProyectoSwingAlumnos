/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import excepciones.ApellidoVacioException;
import excepciones.CalificacionErroneaException;
import excepciones.CarreraVaciaException;
import excepciones.NombreVacioException;
import excepciones.NumeroControlException;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author DELL
 */
public class Alumno implements Serializable {

    private String noControl;
    private String nombre;
    private String paterno;
    private String materno;
    private Double calificacion;
    private Carrera carrera;

    public Alumno(String noControl, String nombre, String paterno, String materno, Double calificacion, Carrera carrera) throws CalificacionErroneaException, NumeroControlException, NombreVacioException, ApellidoVacioException, CarreraVaciaException {
        this.setNoControl(noControl);
        this.setNombre(nombre);
        this.setPaterno(paterno);
        this.materno = materno;
        this.setCalificacion(calificacion);
        this.setCarrera(carrera);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Alumno) {
            Alumno alumno = (Alumno) obj;
            if (this.noControl.compareTo(alumno.getNoControl()) == 0) {
                return true;
            }
        }
        return false;
    }

    public String getNoControl() {
        return noControl;
    }

    public void setNoControl(String noControl) throws NumeroControlException {
        if (!noControl.isEmpty()) {
            this.noControl = noControl;
        } else {
            throw new NumeroControlException("No tiene numero de control");
        }

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) throws NombreVacioException {
        if (!nombre.isEmpty()) {
            this.nombre = nombre;
        } else {
            throw new NombreVacioException("Campo NOMBRE vacio");
        }
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) throws ApellidoVacioException {
        if (!paterno.isEmpty()) {
            this.paterno = paterno;
        } else {
            throw new ApellidoVacioException("Campo Apellido Paterno vacio");
        }

    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public Double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Double calificacion) throws CalificacionErroneaException {
        if ((calificacion >= 0.0) && (calificacion <= 10.0)) {
            this.calificacion = calificacion;
        } else {
            throw new CalificacionErroneaException("Calificacion fuera de rango");
        }

    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) throws CarreraVaciaException {
        if (carrera != null) {
            this.carrera = carrera;
        } else{
            throw new CarreraVaciaException("Ingrese la carrera del alumno");
        }
    }

    public String getString() {
        return String.format("El alumno %s %s %s\nMatricula: %s\nCalificacion: %.2f\nCarrera: %s", nombre, paterno, materno, noControl, calificacion, carrera);
    }
}
