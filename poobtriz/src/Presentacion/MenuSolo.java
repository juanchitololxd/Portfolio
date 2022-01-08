package Presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Controller.GameController;
import dominio.Player;



public class MenuSolo extends JPanel implements ActionListener{
    private MenuPrincipal menuP;
    private JComboBox<String> cColorFondo,cModo;
    private JButton bIniciar,bVolver;
    private JLabel lColorFondo,lModo,lNBuffos,lNombre,lVelocidad;
    private JTextField tNBuffos,tNombre,tVelocidad;
    private int  widthTextF = 110;
    private int widthCampo = 150;
    private int  heigthTextF = 20;
    private int spaceSup = (int) MenuGUI.dimensiones.getHeight()/14;
    private int margenV = 5;
    private int space = (int) (MenuGUI.dimensiones.getHeight()/4) - (heigthTextF*6)-spaceSup + margenV;
    private int posXTexto =(int) MenuGUI.dimensiones.getWidth()/11;
    private int spaceH = posXTexto + widthTextF;
    

    
    
    public MenuSolo()  {
        prepareElementos();
        prepareAcciones();
    }

    
    private void prepareElementos() {
    	this.setLayout(null);
        lNombre = new JLabel("Nombre");
        lColorFondo = new JLabel("Color de Fondo");
        lModo = new JLabel("Modo");
        lVelocidad = new JLabel("Velocidad");
        lNBuffos = new JLabel("Numero de buffos");
        bIniciar = new JButton("Jugar");
        bVolver = new JButton("Volver");
        tNombre = new JTextField();
        tNBuffos = new JTextField();
        tVelocidad = new JTextField();
        cColorFondo = new JComboBox<>();
        cModo = new JComboBox<>();

        cColorFondo.setModel(new DefaultComboBoxModel<>(new String[] { "Default", "none", "green" }));
        cModo.setModel(new DefaultComboBoxModel<>(new String[] { "Uniforme", "Acelerado" }));
   
        //Alinear nombres
        lNombre.setBounds(posXTexto ,spaceSup, widthTextF,heigthTextF);
        add(lNombre);
        tNombre.setBounds(posXTexto + spaceH ,spaceSup, widthCampo,heigthTextF);
        add(tNombre);
        
        lColorFondo.setBounds(posXTexto ,spaceSup+heigthTextF+space, widthCampo,heigthTextF);
        add(lColorFondo);
        cColorFondo.setBounds(posXTexto + spaceH ,spaceSup+heigthTextF+space, widthCampo,heigthTextF);
        add(cColorFondo);
        
        lModo.setBounds(posXTexto ,spaceSup+(heigthTextF*2)+(space*2),widthTextF,heigthTextF);
        add(lModo);
        cModo.setBounds(posXTexto + spaceH ,spaceSup+(heigthTextF*2)+(space*2), widthCampo,heigthTextF);
        add(cModo);
        
        lVelocidad.setBounds(posXTexto ,spaceSup+(heigthTextF*3)+(space*3),widthTextF,heigthTextF);
        add(lVelocidad);
        tVelocidad.setBounds(posXTexto + spaceH  ,spaceSup+(heigthTextF*3)+(space*3), widthCampo,heigthTextF);
        add(tVelocidad);
        
        lNBuffos.setBounds(posXTexto ,spaceSup+(heigthTextF*4)+(space*4),widthTextF,heigthTextF);
        add(lNBuffos);
        tNBuffos.setBounds(posXTexto + spaceH,spaceSup+(heigthTextF*4)+(space*4), widthCampo,heigthTextF);
        add(tNBuffos);
        
        
        bIniciar.setBounds(posXTexto + spaceH,(int) (spaceSup+(heigthTextF*6)+(space*6)), 100,30);
        add(bIniciar);
        
        bVolver.setBounds((int) MenuGUI.dimensiones.getWidth()/9,(int) (spaceSup+(heigthTextF*6)+(space*6)), 100,30);
        add(bVolver);
}
    
 
   
    private void prepareAcciones(){
        this.bIniciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame ("MyPanel");
                frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
                Player p = crearPlayer();
                MenuGUI.game =GameController.onePlayer(p, Integer.valueOf(tNBuffos.getText()));
                //frame.setVisible(true);

            }
        });
        
    	bVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				menuP = new MenuPrincipal();
				getRootPane().getContentPane().add(menuP);
			}
        });
        
    }

	private Player crearPlayer() {
		boolean uniform;
		if(cModo.getSelectedItem() == "Uniforme") uniform = true; else uniform = false;
		return GameController.createPlayer(uniform, Integer.valueOf(tVelocidad.getText()), tNombre.getText(),GameController.selecColor((String)cColorFondo.getSelectedItem()));//dinamizar
	}




    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }

   
}

