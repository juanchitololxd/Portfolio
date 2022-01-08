package Presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Controller.Persistencia;
import dominio.TetrisException;

public class Historial extends JPanel{
    
	private MenuPrincipal menuP;
    private String[] titulos = {"Game", "Fecha", "Tiempo", "Bloques"};
    private String[][] filas;
    private JScrollPane history;
    private JButton bVolver;
    
    private void prepareAcciones() {
    	bVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				menuP = new MenuPrincipal();
				getRootPane().getContentPane().add(menuP);
			}
        });
		
	}

	private void prepareElementos(String nick) throws TetrisException {
		bVolver=new JButton("Volver");
		bVolver.setBounds(20,10,70,30);
	    add(bVolver);
    	
	    filas = Persistencia.importHistory(nick);
    	history = new JScrollPane(new JTable(filas, titulos));
    	history.setBounds((int) MenuGUI.dimensiones.getWidth() /10,50,400,400);
    	add(history); 
    	this.revalidate();
    	this.repaint();
	}

	public Historial(String nick) throws TetrisException {
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
    	setVisible(true);
    	prepareElementos(nick);
        prepareAcciones();
    }
    



    
        
}
