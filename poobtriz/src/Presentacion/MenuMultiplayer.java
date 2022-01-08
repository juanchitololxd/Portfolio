package Presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Controller.GameController;
import dominio.Player;



public class MenuMultiplayer extends JPanel implements ActionListener{
    private MenuPrincipal menuP;
    private JComboBox<String> cColorFondo,cColorFondo1,cModo;
    private JButton bIniciar,bVolver;
    private JLabel lColorFondo,lColorFondo1,lModo,lNBuffos,lNombre,lNombre1,lVelocidad,jPlayer1,jPlayer2;
    private JTextField txtNBuffos,txtNombre,txtNombre1,txtVelocidad;
    
    
    public MenuMultiplayer()  {
        prepareElementos();
        prepareAcciones();

    }


    private void prepareElementos(){

        lNombre = new JLabel("Nombre");
        lColorFondo = new JLabel("Color de Fondo");
        lModo = new JLabel();
        lVelocidad = new JLabel();
        lNBuffos = new JLabel();
        bIniciar = new JButton();
        bVolver = new JButton("Volver");
        txtNombre = new JTextField();
        txtNBuffos = new JTextField();
        txtVelocidad = new JTextField();
        cColorFondo = new JComboBox<>();
        cModo = new JComboBox<>();
        txtNombre1 = new JTextField();
        lNombre1 = new JLabel("Nombre");
        lColorFondo1 = new JLabel("Color de Fondo");
        cColorFondo1 = new JComboBox<>();
        jPlayer1 = new JLabel("Player 1");
        jPlayer2 = new JLabel("Player 2");


        lModo.setText("Modo");

        lVelocidad.setText("Velocidad");

        lNBuffos.setText("Numero de Buffos");

        bIniciar.setText("Jugar");
        
        cColorFondo.setModel(new DefaultComboBoxModel<>(new String[] { "Default", "none","green" }));

        cModo.setModel(new DefaultComboBoxModel<>(new String[] { "Uniforme", "Acelerado" }));

        cColorFondo1.setModel(new DefaultComboBoxModel<>(new String[] { "Default", "none","green" }));



        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(146, 146, 146)
                .addComponent(jPlayer1)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPlayer2)
                .addGap(175, 175, 175))
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(lColorFondo)
                    .addComponent(lNombre))
                .addGap(74, 74, 74)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(cColorFondo, 0, 107, Short.MAX_VALUE)
                    .addComponent(txtNombre))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(lColorFondo1)
                    .addComponent(lNombre1))
                .addGap(74, 74, 74)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(cColorFondo1, 0, 107, Short.MAX_VALUE)
                    .addComponent(txtNombre1))
                .addGap(60, 60, 60))
            .addGroup(layout.createSequentialGroup()
                .addGap(304, 304, 304)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bVolver)
                        .addGap(110, 110, 110)
                        .addComponent(bIniciar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(lNBuffos)
                            .addComponent(lVelocidad)
                            .addComponent(lModo))
                        .addGap(61, 61, 61)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(cModo, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNBuffos)
                            .addComponent(txtVelocidad, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(311, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jPlayer1)
                    .addComponent(jPlayer2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(lNombre1)
                            .addComponent(txtNombre1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(lColorFondo1)
                            .addComponent(cColorFondo1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(lNombre)
                            .addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(lColorFondo)
                            .addComponent(cColorFondo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(cModo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(lModo))
                        .addGap(26, 26, 26))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(lVelocidad)
                            .addComponent(txtVelocidad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(lNBuffos)
                            .addComponent(txtNBuffos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(bIniciar)
                    .addComponent(bVolver))
                .addContainerGap(41, Short.MAX_VALUE))
        );
    }
    
    private void prepareAcciones() {
    this.bIniciar.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            JFrame frame = new JFrame ("MyPanel");
            frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
            Player p1 = crearPlayer1();
            Player p2 = crearPlayer2();
            MenuGUI.game =GameController.twoPlayer(p1,p2, Integer.valueOf(txtNBuffos.getText()));
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
    
	private Player crearPlayer1() {
		boolean uniform;
		if(cModo.getSelectedItem() == "Uniforme") uniform = true; else uniform = false;
		return GameController.createPlayer(uniform, Integer.valueOf(txtVelocidad.getText()), txtNombre.getText(), GameController.selecColor((String)cColorFondo.getSelectedItem()));//dinamizar
	}
	
	private Player crearPlayer2() {
		boolean uniform;
		if(cModo.getSelectedItem() == "Uniforme") uniform = true; else uniform = false;
		return GameController.createPlayer(uniform, Integer.valueOf(txtVelocidad.getText()), txtNombre1.getText(), GameController.selecColor((String)cColorFondo1.getSelectedItem()));//dinamizar
	}

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }

}