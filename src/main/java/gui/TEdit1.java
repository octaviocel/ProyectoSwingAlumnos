/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import excepciones.DoubleException;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author DELL
 */
public class TEdit1 extends JPanel {

    private JLabel lblcaption;
    private JTextField edit;

    public TEdit1(String caption) {

        this.lblcaption = new JLabel(caption);
        this.edit = new JTextField(15);

        super.add(lblcaption);
        super.add(edit);
    }

    public void clear() {
        edit.setText("");
    }

    public Double getValue() throws DoubleException {
        try {
            return Double.parseDouble(edit.getText());
        } catch (Exception e) {
            throw new DoubleException("Ponga una calificacion valida");
        }
    }
    
    public void setCalificacion(Double cal){
        edit.setText(Double.toString(cal));
    }
}
