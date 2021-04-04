/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import excepciones.ApellidoVacioException;
import excepciones.CalificacionErroneaException;
import excepciones.CarreraVaciaException;
import excepciones.DoubleException;
import excepciones.NombreVacioException;
import excepciones.NumeroControlException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import model.Alumno;
import model.Carrera;

/**
 *
 * @author DELL
 */
public class AlumnoDialogo extends JDialog {

    private final String[] COMBO = {"", "SISTEMAS", "NUTRICION", "ARQUITECTURA", "CIVIL", "CONTADURIA"};

    private TEdit edtNumero;
    private TEdit edtNombre;
    private TEdit edtPaterno;
    private TEdit edtMaterno;
    private TEdit1 edtCalificacion;

    private JLabel lblCarrera;
    private JComboBox cbCarrera;
    private JPanel pnlCarrera;

    private JButton btnAceptar;
    private JButton btnCancelar;
    private JPanel pnlBoton;

    private AgregarAlumnosListener listener;

    public AlumnoDialogo(JFrame parent) {
        super(parent, true);
        super.setSize(350, 500);
        super.setLayout(new GridLayout(7, 1));
        super.setLocationRelativeTo(parent);

        edtNumero = new TEdit("No. Control: ");
        edtNombre = new TEdit("Nombre: ");
        edtPaterno = new TEdit("Apellido parterno: ");
        edtMaterno = new TEdit("Apellido Materno: ");
        edtCalificacion = new TEdit1("Calificacion: ");

        cbCarrera = new JComboBox(COMBO);
        lblCarrera = new JLabel("Carrera: ");
        pnlCarrera = new JPanel();
        pnlCarrera.add(lblCarrera);
        pnlCarrera.add(cbCarrera);

        pnlBoton = new JPanel();
        pnlBoton.setLayout(new FlowLayout());
        btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Alumno al = new Alumno(edtNumero.getValue(),
                            edtNombre.getValue(),
                            edtPaterno.getValue(),
                            edtMaterno.getValue(),
                            edtCalificacion.getValue(),
                            setCarrera(cbCarrera.getSelectedIndex()));
                    listener.aceptarClick(al);
                    //limpiar();
                } catch ( NumeroControlException | NombreVacioException | ApellidoVacioException | CalificacionErroneaException | CarreraVaciaException | DoubleException ex) {
                    JOptionPane.showMessageDialog(AlumnoDialogo.this,
                            ex.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                } 
            }
        });
        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AlumnoDialogo.this.setVisible(false);
                limpiar();
            }
        });
        pnlBoton.add(btnAceptar);
        pnlBoton.add(btnCancelar);

        super.add(edtNumero);
        super.add(edtNombre);
        super.add(edtPaterno);
        super.add(edtMaterno);
        super.add(edtCalificacion);
        super.add(pnlCarrera);
        super.add(pnlBoton);
    }

    public void setListener(AgregarAlumnosListener listener) {
        this.listener = listener;
    }

    private Carrera setCarrera(Integer i) {
        switch (i) {
            case 0:
                return null;
            case 1:
                return Carrera.SISTEMAS;
            case 2:
                return Carrera.NUTRICION;
            case 3:
                return Carrera.ARQUITECTURA;
            case 4:
                return Carrera.CIVIL;
            case 5:
                return Carrera.CONTADURIA;
            default:
                return null;
        }
    }

    private void limpiar() {
        edtNumero.clear();
        edtNombre.clear();
        edtPaterno.clear();
        edtMaterno.clear();
        edtCalificacion.clear();
        cbCarrera.setSelectedIndex(0);
    }
    
}
