/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import java.awt.*;
import java.awt.Image;
import javax.swing.*;

/**
 *
 * @author DELL
 */
public class AcercaDialog extends JDialog{
    
    private JPanel pnls;
    private JLabel lbl;
    private JPanel pnla;
    private JLabel lbl1;
    private JLabel lbl2;
    private JLabel lbl3;
    
    public AcercaDialog(JFrame parent){
        super(parent,true);
        super.setSize(300, 400);
        super.setLocationRelativeTo(null);
        super.setLayout(new FlowLayout());
        
        pnls = new JPanel();
        pnls.setLayout(new BorderLayout());        
        lbl = new JLabel();
        //Dimension dim= new Dimension(200,150);
        //lbl.setPreferredSize(dim);
        Image img= new ImageIcon("lasalle.png").getImage();
        ImageIcon icon=new ImageIcon(img.getScaledInstance(130, 130, Image.SCALE_SMOOTH));
        //ImageIcon icon= new ImageIcon("lasalle.png");        
        lbl.setIcon(icon);
        pnls.add(lbl);
        
        pnla = new JPanel();
        pnla.setLayout(new BorderLayout());
        lbl1 = new JLabel("Universidad La Salle Oaxaca");
        lbl2 = new JLabel(" Creado por Octavio Celaya");
        lbl3 = new JLabel("    Derechos reservados");
        pnla.add(lbl1, BorderLayout.NORTH);
        pnla.add(lbl2, BorderLayout.CENTER);
        pnla.add(lbl3, BorderLayout.SOUTH);
        
        super.add(pnls);
        super.add(pnla);
        super.pack();
    }
}
