package Presentacion;

import java.awt.Dimension;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Controller.Persistencia;

import java.awt.event.*;
import dominio.Log;
import dominio.Player;
import dominio.Tablero;
import dominio.TetrisException;

public class Game extends JFrame implements ActionListener, KeyListener{
	private TableroGUI t1;
	private TableroGUI t2 = null;
	private Dimension screenSize =java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	private int buffs;
	private JPanel interfaz = new JPanel();
	private JButton end = new JButton("Terminar");
	
	public Game(Player p1, Player p2, int buffos) {
		this.buffs = buffos;
		this.setSize(screenSize);
		setLocationRelativeTo(null);
		setVisible(true);
		prepareInterfaz(p1, p2);
		prepareAcciones();
		startGame();
	}
	
	private void prepareInterfaz(Player p1, Player p2) {
		interfaz.setLayout(null);
		crearTableros(p1, p2);
		interfaz.add(end);
		getContentPane().add(interfaz);
		
	}

	/**Crea los tableros indicados
	 * @param numTableros es el numero de la tableros
	 */

	private void crearTableros(Player p1, Player p2) {
		if(p2 == null) t1 = new TableroGUI((int) screenSize.getWidth()*5/14, p1);
		else {
			t1 = new TableroGUI((int) screenSize.getWidth()/14, p1);//dinamizar
			setVisible(true);
			t2 = new TableroGUI ((int) screenSize.getWidth()*8/14, p2);
			interfaz.add(t2);
		}
		interfaz.add(t1);
		

		
		
	}

	/** Asigna controles a cada tablero 
	 */
	
	private void prepareAcciones() {

		interfaz.addKeyListener(t1.getPlayer());		
		this.addKeyListener(t1.getPlayer());

		if(t2 != null) {
			interfaz.addKeyListener(t2.getPlayer());
			this.addKeyListener(t2.getPlayer());
		}
		
		this.addKeyListener(this);
		interfaz.addKeyListener(this);
		
		interfaz.setFocusable(true);
		add(interfaz);
		setVisible(true);
		/*
	    guardar.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent ev){
	        	guardar();
	        }

	    });
		*/
        WindowListener w = new WindowAdapter() { 
                public void windowClosing(WindowEvent e) {
                    fin();
                }

            };  
        this.addWindowListener(w);
	}

	public void pausar() {
		Tablero.pausar();
	}
	
	private boolean areAlive() {
		if(t1.isAlive() && (t2 == null || t2.isAlive())) return true;
		return false;
	}
	
	private void closeThreads() {
		if(t1.isAlive()) t1.close();
		if(t2 != null && t2.isAlive()) t2.close();
	}

	
	public void guardar() {
		try {
			if(areAlive()) pausar();
	        JFileChooser fileChooser = new JFileChooser();
	        fileChooser.showSaveDialog(this);
	        File file = fileChooser.getSelectedFile();
	        
			if(t2 == null) Persistencia.guardeGame(t1.getPlayer(), file);
			else Persistencia.guardeGame(t1.getPlayer(), t2.getPlayer(), file);
		}catch(TetrisException e) {
			Log.registre(e);
			JOptionPane.showMessageDialog(new JPanel(), e.getMessage());
		}
	}
	
	

	private void fin() {
		this.closeThreads();
		System.exit(0);
		
	}

	/** Empieza el juego creando y deslizando bloques lo que mas pueda
	 */

	public void startGame() {
		
		t1.startGame();
		if(t2 != null) t2.startGame();
		Tablero.despausar();
		Tablero.prepareBuffos(buffs);
		
		Timer timer = new Timer();
		TimerTask velDown = new TimerTask() {
			public void run() {
				boolean hayVivo = false;
				for(Tablero t: Tablero.tableros) {
					if(t.isAlive()) hayVivo = true;
				}				
				if(!hayVivo) {
					JOptionPane.showConfirmDialog(null, "Ha acabado el juego", "Warning",JOptionPane.PLAIN_MESSAGE);
					this.cancel();
					timer.cancel();
				}
			}
		};
		timer.schedule(velDown,1000,1000);
		
	}
	


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void despausar() {
		Tablero.despausar();
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if( e.getKeyCode() == KeyEvent.VK_P) {
			int dialogResult = JOptionPane.showConfirmDialog (null, "Desea guardar el juego?","Warning",JOptionPane.YES_NO_OPTION);
			if(dialogResult == JOptionPane.YES_OPTION){
			  guardar();
			}
			this.despausar();
		}
	}

}
 