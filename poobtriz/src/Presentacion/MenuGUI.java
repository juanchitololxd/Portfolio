package Presentacion;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.*;

import Controller.GameController;
import Controller.Persistencia;
import dominio.Player;



public class MenuGUI extends JFrame implements ActionListener{
    public static Dimension dimensiones = getDataPantalla();
    private JMenuBar barra;
    private JMenu menu;
    private JMenuItem abrir,guardar;
    private MenuPrincipal start = new MenuPrincipal();
    public static Game game;
    
    private MenuGUI()  {
        setTitle("POOBTRIZ");
        setResizable(false);
        getDataPantalla();
        prepareElementosMenu();
        prepareElementos();
        prepareAcciones();
        getContentPane().add(start);
        setVisible(true);

    }

    private static Dimension getDataPantalla(){
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        return screenSize;
        
    }

    private void prepareElementos(){
        setBounds(0,0,(int) dimensiones.getWidth()/2,(int) dimensiones.getHeight()/2);
        setLocationRelativeTo(null);
        
    }
    


    private void prepareElementosMenu(){
        barra = new JMenuBar();
        setJMenuBar(barra);
        menu = new JMenu("Archivo");
        barra.add(menu);
        abrir = new JMenuItem("Abrir");
        abrir.addActionListener(this);
        menu.addSeparator();
        menu.add(abrir);
        guardar = new JMenuItem("Guardar");
        guardar.addActionListener(this);
        menu.add(guardar);
    }




    public void prepareAcciones(){
   
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter()    {         
            public void windowClosing(WindowEvent e){
                salga();
        }
    });

    
    guardar.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent ev){
        	salvarArchivo();
        }

    });

    abrir.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent ev){
        	abrirArchivo();
        }

    });
}



private void abrirArchivo() {
        JFileChooser file=new JFileChooser();
        file.showOpenDialog(this);
		List<Player> players =  Persistencia.abrirPlayers(file.getSelectedFile());
		if(players.size() == 1) game = GameController.createGame(players.get(0), null, players.get(0).getNumBuffs());
		else game = GameController.createGame(players.get(0), players.get(1), players.get(0).getNumBuffs());
}    
  

private void salvarArchivo() {
    JFileChooser file=new JFileChooser();
    file.showSaveDialog(this);
}


private void salga(){
	System.exit(0);            
}



    @Override
    public void actionPerformed(ActionEvent e) {}

    public static void main(String[] args) {
    	@SuppressWarnings("unused")
		MenuGUI MenuPT =new MenuGUI();
      }
}

