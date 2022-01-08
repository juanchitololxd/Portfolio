package Presentacion;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import dominio.TetrisException;

public class MenuPrincipal extends JPanel{
    private JButton solo,multiplayer,vsMaquina,Historial;
    private JLabel Titulo,subTitulo;
    private MenuSolo menu = new MenuSolo();
    private MenuMaquina menuM = new MenuMaquina();
    private MenuMultiplayer menuMu = new MenuMultiplayer();
    private Historial history; 
    
    
    
    public MenuPrincipal() {
    	setLayout(null);
    	initComponents();
    	prepareElementos();
    }
    
    private void initComponents() {
    	

    	solo = new JButton("Solo");
    	multiplayer = new JButton("Multiplayer");
    	vsMaquina = new JButton("vs Maquina");
    	Historial = new JButton("Ver Historial");
    	Titulo = new JLabel("POOBTRIZ");
    	subTitulo = new JLabel("ELIGE UN MODO DE JUEGO");
        
    	Titulo.setFont(new Font("tahoma", 0, 32));
        Titulo.setBounds((int)MenuGUI.dimensiones.getWidth()/5,0,200,150);
  
        subTitulo.setFont(new Font("tahoma", 0, 16));
        subTitulo.setBounds((int)(MenuGUI.dimensiones.getWidth()/5)-15,80,250,100);
        
        solo.setBounds((int)MenuGUI.dimensiones.getWidth()/20,(int) (MenuGUI.dimensiones.getHeight()/4),70,30);
        multiplayer.setBounds((int)MenuGUI.dimensiones.getWidth()* 2/5,(int) MenuGUI.dimensiones.getHeight()/4,100,30);
        vsMaquina.setBounds((int)MenuGUI.dimensiones.getWidth()* 2/9,(int) MenuGUI.dimensiones.getHeight()/4,100,30);
        Historial.setBounds((int)MenuGUI.dimensiones.getWidth()* 2/9,(int) MenuGUI.dimensiones.getHeight()/3,110,30);
	    
        
        add(solo);
        add(multiplayer);
        add(vsMaquina);
        add(Historial);
        add(Titulo);
        add(subTitulo);
        this.revalidate();
    	this.repaint();

    }
    
    private void prepareElementos(){
        setVisible(true);
        solo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				getRootPane().getContentPane().add(menu);
				
			}
        });
		
		multiplayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				getRootPane().getContentPane().add(menuMu);
					
				}
        });
		
		vsMaquina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				getRootPane().getContentPane().add(menuM);
					
				}
        });
		
		Historial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input = JOptionPane.showInputDialog("Usuario");
			    System.out.println(input);
			    setVisible(false);
			    try {
			    	history = new Historial(input);	
			    	getRootPane().getContentPane().add(history);
			    }catch(TetrisException er) {
			    JOptionPane.showConfirmDialog(null, er.getMessage(),"Warning",JOptionPane.PLAIN_MESSAGE);
			    }
				
				
					
				}
        });
        
    }
}
