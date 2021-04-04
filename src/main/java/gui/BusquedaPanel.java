/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author DELL
 */
public class BusquedaPanel extends JPanel {

    private JLabel lblBuscar;
    private JTextField edtBuscar;
    private JButton btnBuscar;

    private BuscarAlumnoListener listener;

    public BusquedaPanel() {
        //super.setBackground(Color.red);
        super.setLayout(new FlowLayout());

        lblBuscar = new JLabel("Buscar: ");
        edtBuscar = new JTextField(15);
        btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.buscar(edtBuscar.getText());
                edtBuscar.setText("");
            }
        });

        super.add(lblBuscar);
        super.add(edtBuscar);
        super.add(btnBuscar);
    }

    public void setListener(BuscarAlumnoListener listener) {
        this.listener = listener;
    }
}
