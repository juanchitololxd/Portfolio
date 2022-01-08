package Controller;
import java.time.LocalDate;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import dominio.Jugador;
import dominio.Log;
import dominio.Player;
import dominio.TetrisException;

public class Persistencia {
	public static boolean huboRecord(String player, int puntos) {
		try {
			int maxPoints = 0;
			BufferedReader bin = new BufferedReader(
					new FileReader("./Puntuaciones/" + player+".txt"));
			String line = bin.readLine();
			while(line != null) {
				String[] aux = line.split("\\|");
				if(Integer.valueOf(aux[2]) > maxPoints) maxPoints = Integer.valueOf(aux[2]);
				line = bin.readLine();
			}
			bin.close();
			if(puntos >= maxPoints) return true;
			return false;
		} catch (Exception e) {}
		return false;
	}

	 /**
	 * Guarda el juego de dos jugadores
	 * @param jugador 1
	 * @param jugador 2
	 * @param el archivo a guardar
	 */
	public static void guardeGame(Player p1, Player p2, File file) throws TetrisException {
        try {
            OutputStream os = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(p1);
            oos.writeObject(p2);
            oos.close();
            
        } catch (IOException e) {
        	Log.registre(e);
        	throw new TetrisException(TetrisException.PROBLEM_SAVE_FILE);
        }
	}
	
	 /**
	 * Guarda el juego de dos jugadores
	 * @param jugador 1
	 * @param el archivo a guardar
	 */
	public static void guardeGame(Player p, File file) throws TetrisException {
        try {
            OutputStream os = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(p);
            oos.close();
            
        } catch (IOException e) {
		throw new TetrisException(TetrisException.PROBLEM_SAVE_FILE);
        }
		
	}
	
	 /**
	 * Carga la lista de jugadores
	 * @param el archivo a cargar
	 */
	
	public static List<Player> abrirPlayers(File file) {
		List<Player> players = new ArrayList<Player>();
        try {
            InputStream is = new FileInputStream(file.getAbsolutePath());
            ObjectInputStream ois = new ObjectInputStream(is);
			 players.add((Player) ois.readObject());
			 players.add((Player) ois.readObject());
            
        } catch (ClassNotFoundException | IOException e) {}
        return players;
	}


	 /**
	 * Guarda la puntuacion de los jeugos
	 * @param nombre del jugador
	 * @param el jugador como tal
	 */
	
	public static void registrePuntuacion(String nick, Jugador t) {
        try {
        	File file = new File("./Puntuaciones/" + nick.trim() + ".txt");
        	FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            fw.append(LocalDate.now() + "|"+ String.valueOf( t.getTiempo()) + "|" +  String.valueOf(t.getPuntuacionBLoq()) + "\n" );
            fw.close();
        } catch (IOException e) {
        	Log.registre(e);
        }
		
	}
	

	 /**
	 * Importa el historial de puntuacion de un jugador
	 * @param nombre del jugador
	 * @throws TetrisException 
	 */
	public static String[][] importHistory(String nick) throws TetrisException {
		String[][] data = null;
    	try {
    	File file = new File("./Puntuaciones/" + nick.trim() + ".txt");
    	int filas = contarLineas(file);
    	 data = new String[filas][4];
		@SuppressWarnings("resource")
		Scanner s = new Scanner(file);
		int cont = 1;
		while(s.hasNextLine()) {
			String x = s.nextLine();
			String[] line =x.split("\\|");//date|time|bloque
			String[] aux = {String.valueOf(cont), line[0], line[1], line[2]};
			data[cont-1] = aux.clone();
			cont++;
			}
		} catch (Exception e) {
			Log.registre(e);
			throw new TetrisException(TetrisException.INVALID_USER);
			
		}
    	
    	return data;
    	
    	
    	
	}


	 /**
	 * Cuenta cuantas lineas tiene el documento
	 * @param El documento
	 */
	private static int contarLineas(File file) throws FileNotFoundException {
		@SuppressWarnings("resource")
		Scanner iterate = new Scanner(file);
		int numLines=0;
		 while(iterate.hasNextLine()) { 
			 iterate.nextLine(); numLines++; 
			 }
		
		return numLines;
		
	}
	
}
