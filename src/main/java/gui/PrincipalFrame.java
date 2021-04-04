/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import controlador.Controller;
import excepciones.AlumnoExistenteException;
import excepciones.ApellidoVacioException;
import excepciones.ArchivoInvalidoException;
import excepciones.CalificacionErroneaException;
import excepciones.CargarArchivoException;
import excepciones.CarreraVaciaException;
import excepciones.GuardarArchivoException;
import excepciones.NombreVacioException;
import excepciones.NumeroControlException;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.SwingUtilities;
import model.Alumno;

/**
 *
 * @author DELL
 */
public class PrincipalFrame extends JFrame {

    private BusquedaPanel pnlBusqueda;
    private JTable tblAlumnos;
    private Controller controlador;
    private AlumnoDialogo dlgAlumno;
    private AcercaDialog dlgAcerca;
    private EliminarDialog dlgEliminar;
    private ModificarDialog dlgModificar;

    private AlumnosTableModel modelAlumno;

    public PrincipalFrame() {
        super("Control Escolar");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setSize(800, 350);
        super.setLayout(new BorderLayout());
        super.setLocationRelativeTo(null);

        try {
            controlador = new Controller();
        } catch (ArchivoInvalidoException ex) {
            JOptionPane.showMessageDialog(PrincipalFrame.this,
                    ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (CargarArchivoException ex) {
            JOptionPane.showMessageDialog(PrincipalFrame.this,
                    ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        modelAlumno = new AlumnosTableModel(controlador);
        tblAlumnos = new JTable(modelAlumno);
        pnlBusqueda = new BusquedaPanel();
        dlgAlumno = new AlumnoDialogo(this);
        dlgAlumno.setListener(new AgregarAlumnosListener() {
            @Override
            public void aceptarClick(Alumno alumno) {
                try {
                    controlador.add(alumno);
                    dlgAlumno.setVisible(false);
                    modelAlumno.fireTableDataChanged();
                    //PrincipalFrame.this.pack();
                } catch (AlumnoExistenteException ex) {
                    JOptionPane.showMessageDialog(PrincipalFrame.this,
                            "La matricula ya ha sido insertada",
                            "Matricula invalida",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        dlgEliminar = new EliminarDialog(this);
        dlgEliminar.setListener(new EliminarListener() {
            @Override
            public void eliminar(String g) {
                Alumno a = controlador.buscar(g);
                if (a != null) {
                    int resp = JOptionPane.showConfirmDialog(
                            null,
                            "¿Esta seguro?",
                            "Elimina Usuario",
                            JOptionPane.YES_NO_OPTION);
                    if (JOptionPane.OK_OPTION == resp) {
                        controlador.eliminarAlumno(a);
                        modelAlumno.fireTableDataChanged();
                        dlgEliminar.setVisible(false);
                        JOptionPane.showMessageDialog(
                                PrincipalFrame.this,
                                "Eliminado de manera exitosa",
                                "Usuario Eliminado",
                                JOptionPane.INFORMATION_MESSAGE);

                    } else {
                        dlgEliminar.setVisible(false);
                    }

                } else {
                    JOptionPane.showMessageDialog(
                            PrincipalFrame.this,
                            "Matricula no existente\nPor favor ingresa una matricula valida",
                            "Usuario no Eliminado",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        dlgAcerca = new AcercaDialog(this);
        super.setJMenuBar(makeMenu());

        pnlBusqueda.setListener(new BuscarAlumnoListener() {
            @Override
            public void buscar(String a) {
                Alumno s = controlador.buscar(a);
                if (s != null) {
                    JOptionPane.showMessageDialog(
                            null,
                            s.getString(),
                            "Alumno",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(
                            null,
                            "Alumno no encontrado",
                            "No encontrado",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }

        });
        dlgModificar = new ModificarDialog(this);
        dlgModificar.setListener(new ModificarListener() {
            @Override
            public void modificarClick(Alumno alumno) {
//                                Alumno copy = alumno;
//                                controlador.eliminarAlumno(copy);
                try {
                    controlador.add(alumno);
                } catch (AlumnoExistenteException ex) {
                    JOptionPane.showMessageDialog(
                            null,
                            ex.getMessage(),
                            "No se ha podido modificar",
                            JOptionPane.ERROR_MESSAGE);
                }
                modelAlumno.fireTableDataChanged();
                dlgModificar.setVisible(false);
            }
        });
        tblAlumnos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int resp = JOptionPane.showConfirmDialog(
                            null,
                            "¿Quieres editar este alumno?",
                            "Editar Usuario",
                            JOptionPane.YES_NO_OPTION);
                    if (JOptionPane.OK_OPTION == resp) {
                        int filaseleccionada = tblAlumnos.getSelectedRow();
                        dlgModificar.setMatricula(tblAlumnos.getValueAt(filaseleccionada, 0).toString());
                        dlgModificar.setNombre(tblAlumnos.getValueAt(filaseleccionada, 1).toString());
                        dlgModificar.setPaterno(tblAlumnos.getValueAt(filaseleccionada, 2).toString());
                        dlgModificar.setMaterno(tblAlumnos.getValueAt(filaseleccionada, 3).toString());
                        dlgModificar.setCalificacion(Double.valueOf(tblAlumnos.getValueAt(filaseleccionada, 4).toString()));
                        dlgModificar.setCar(ModificarDialog.setCarre(tblAlumnos.getValueAt(filaseleccionada, 5).toString()));

                        try {
                            Alumno eliminado = new Alumno(
                                    tblAlumnos.getValueAt(filaseleccionada, 0).toString(),
                                    tblAlumnos.getValueAt(filaseleccionada, 1).toString(),
                                    tblAlumnos.getValueAt(filaseleccionada, 2).toString(),
                                    tblAlumnos.getValueAt(filaseleccionada, 3).toString(),
                                    Double.valueOf(tblAlumnos.getValueAt(filaseleccionada, 4).toString()),
                                    dlgModificar.setCarrera(ModificarDialog.setCarre(tblAlumnos.getValueAt(filaseleccionada, 5).toString())));
                            controlador.eliminarAlumno(eliminado);
                        } catch (CalificacionErroneaException | NumeroControlException | NombreVacioException | ApellidoVacioException | CarreraVaciaException ex) {
                            JOptionPane.showMessageDialog(
                                    null,
                                    ex.getMessage(),
                                    "No se ha podido modificar",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                        dlgModificar.setVisible(true);

                    }
                }
            }
        });

        super.add(new JScrollPane(tblAlumnos), BorderLayout.CENTER);
        super.add(pnlBusqueda, BorderLayout.SOUTH);
        super.setVisible(true);
    }

    private JMenuBar makeMenu() {
        JMenuBar menu = new JMenuBar();

        JMenu mAlumnos = new JMenu("Alumnos");
        JMenuItem miItem = new JMenuItem("Nuevo");
        miItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dlgAlumno.setVisible(true);
                //System.out.println("Apreto");
            }
        });
        JMenuItem miEliminar = new JMenuItem("Eliminar");
        miEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dlgEliminar.setVisible(true);
            }
        });

        JMenuItem miGuardar = new JMenuItem("Guardar");
        miGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controlador.guardar();
                } catch (GuardarArchivoException ex) {
                    JOptionPane.showMessageDialog(PrincipalFrame.this,
                            ex.getMessage(),
                            "Error al guardar",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        JMenuItem miSalir = new JMenuItem("Salir");
        miSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        mAlumnos.add(miItem);
        mAlumnos.add(miEliminar);
        mAlumnos.add(miGuardar);
        mAlumnos.addSeparator();
        mAlumnos.add(miSalir);

        JMenu mAyuda = new JMenu("Ayuda");
        JMenuItem miAcerca = new JMenuItem("Acerca de..");
        miAcerca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dlgAcerca.setVisible(true);
            }
        });
        mAyuda.add(miAcerca);

        menu.add(mAlumnos);
        menu.add(mAyuda);

        return menu;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PrincipalFrame();
            }
        });
    }
}
