package Presentacion;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Controller.GameController;
import dominio.Maquina;
import dominio.Player;



public class MenuMaquina extends JPanel implements ActionListener{
	private MenuPrincipal menuP;
    private JComboBox<String> cColorFondo,cModo,cMaquina;
    private JButton bIniciar,bVolver;
    private JLabel lColorFondo,lModo,lNBuffos,lNombre,lVelocidad,lMaquina;
    private JTextField txtNBuffos,txtNombre,txtVelocidad;
    
    
    public MenuMaquina()  {
        prepareElementos();
        prepareAcciones();

    }


    private void prepareElementos(){

        lNombre = new JLabel();
        lColorFondo = new JLabel();
        lModo = new JLabel();
        lVelocidad = new JLabel();
        lNBuffos = new JLabel();
        lMaquina = new JLabel();
        bIniciar = new JButton();
        bVolver = new JButton();
        txtNombre = new JTextField();
        txtNBuffos = new JTextField();
        txtVelocidad = new JTextField();
        cColorFondo = new JComboBox<>();
        cModo = new JComboBox<>();
        cMaquina = new JComboBox<>();

        lNombre.setText("Nombre");

        lColorFondo.setText("Color de Fondo");

        lModo.setText("Modo");

        lVelocidad.setText("Velocidad");

        lNBuffos.setText("Numero de Buffos");

        bIniciar.setText("Jugar");
        
        bVolver.setText("Volver");

        txtNombre.setText("");

        txtNBuffos.setText("");

        txtVelocidad.setText("");
        
        lMaquina.setText("Maquina");

        cColorFondo.setModel(new DefaultComboBoxModel<>(new String[] { "Default", "none", "green" }));

        cModo.setModel(new DefaultComboBoxModel<>(new String[] { "Uniforme", "Acelerado" }));
        
        cMaquina.setModel(new DefaultComboBoxModel<>(new String[] { "Principiante", "Experto" }));
   
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(bVolver)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(lNBuffos)
                            .addComponent(lVelocidad)
                            .addComponent(lModo)
                            .addComponent(lColorFondo)
                            .addComponent(lNombre)))
                    .addComponent(lMaquina))
                .addGap(61, 61, 61)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(cModo, 0, 107, Short.MAX_VALUE)
                    .addComponent(cColorFondo, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNombre)
                    .addComponent(txtVelocidad)
                    .addComponent(txtNBuffos)
                    .addComponent(bIniciar)
                    .addComponent(cMaquina, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(lMaquina)
                    .addComponent(cMaquina, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lNombre)
                    .addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lColorFondo)
                    .addComponent(cColorFondo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lModo)
                    .addComponent(cModo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(lVelocidad)
                    .addComponent(txtVelocidad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lNBuffos)
                    .addComponent(txtNBuffos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(bIniciar)
                    .addComponent(bVolver))
                .addContainerGap(22, Short.MAX_VALUE))
        );
    }

    private void prepareAcciones(){
        this.bIniciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame ("MyPanel");
                frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
                Player p = crearPlayer();
                Maquina m = crearMaquina();
                MenuGUI.game =GameController.twoPlayer(p, m, Integer.valueOf(txtNBuffos.getText()));
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
		return GameController.createPlayer(uniform, Integer.valueOf(txtVelocidad.getText()), txtNombre.getText(), GameController.selecColor((String)cColorFondo.getSelectedItem()));//dinamizar
	}
	

	private Maquina crearMaquina() {
		boolean uniform;
		if(cModo.getSelectedItem() == "Uniforme") uniform = true; else uniform = false;
		if(cMaquina.getSelectedItem() == "Principiante") {
			return GameController.createMaquinaNoob(uniform,Integer.valueOf(txtVelocidad.getText()), Color.gray);
		}
		return GameController.createMaquinaPro(uniform,Integer.valueOf(txtVelocidad.getText()), Color.gray);
	}


    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }

}
