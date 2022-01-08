package Controller;

import Presentacion.Game;
import dominio.Jugador;
import dominio.Maquina;
import dominio.MaquinaPrincipiante;
import dominio.MaquinaPro;
import dominio.Player;
import dominio.Tablero;

import java.awt.Color;

public class GameController {
	private static int velInicial = 1200;
	
	 /**
	 * Crea una maquina experta
	 * @param si es uniforme o no
	 * @param velocidad con la que juega el maquino
	 * @param color de tablero
	 * @return Maquina Experta
	 */
	public static Maquina createMaquinaPro(boolean uniform, int vel,Color c) {
		if(!uniform) vel = velInicial;	
		return new MaquinaPro(new Tablero(uniform, vel, c));
	}
	

	 /**
	 * Crea una maquina principiante
	 * @param si es uniforme o no
	 * @param velocidad con la que juega el maquino
	 * @param color de tablero
	 * @return Maquina principiante
	 */	
	public static Maquina createMaquinaNoob(boolean uniform, int vel,Color c) {
		if(!uniform) vel = velInicial;	
		return new MaquinaPrincipiante(new Tablero(uniform, vel, c));
	}
	

	/**
	 * Crea una jugador
	 * @param si es uniforme o no
	 * @param velocidad con la que juega el jugador
	 * @param nombre del jugador
	 * @param color de tablero
	 * @return nuevo jugador
	 */
	public static Player createPlayer(boolean uniform, int vel,String nick,Color c) {
		if(!uniform) vel = velInicial;	
		return new Jugador(nick, new Tablero(uniform, vel, c));
	}
	
	/**
	 * Crea un nuevo juego con 2 jugadores y un numero de buffos
	 * @param jugador 1
	 * @param jugador 2
	 * @param numero de buffos
	 */
	public static Game createGame(Player p1, Player p2, int buffos) {
		if(p2 == null)
			return GameController.onePlayer(p1, buffos);
		else
			return GameController.twoPlayer(p1, p2, buffos);
	}
	
	/**
	 * Crea el jugador 1 
	 * @param jugador 1
	 * @param numero de buffos
	 */
	public static Game onePlayer(Player p1, int buffos) {
		if(!p1.isUniform()) p1.setVelocidad(velInicial);
		return new Game(p1,null, buffos);
	}
	
	/**
	 * Crea el jugador 2
	 * @param jugador 1
	 * @param jugador 2
	 * @param numero de buffos
	 */
	public static Game twoPlayer(Player p1, Player p2, int buffos) {
		if(!p1.isUniform()) {
			p1.setVelocidad(velInicial);
		}
		return new Game(p1, p2, buffos);
	}
	
	public static Color selecColor(String c) {
		switch(c) {
			case "Default": return Color.LIGHT_GRAY;
			case "none": return Color.black;
			case "green": return Color.green;
		}
		return null;
	}
	
}
