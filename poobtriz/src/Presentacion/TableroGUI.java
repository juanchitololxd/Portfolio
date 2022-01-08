package Presentacion;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;
import dominio.*;

public class TableroGUI extends JPanel{
	private Player player;
	private int w = 250; // Dinamizar (opcional)
	private int h;
	private int x;
	private int y = 50;
	private int squareSize;
	private int sizeData = 120;
	
	
	public TableroGUI(int xPos, Player player) {
	    this.setFocusable(true);
		this.player = player;
		this.x = xPos;
		squareSize = (int) w/ Tablero.cols;
		this.h = squareSize * Tablero.filas;
		this.setBounds(x,y,w+ sizeData,h);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setFont(new Font("Verdana", Font.BOLD, 13));
		if(player.getColorBg() == null) drawMatriz(g);
		drawBackground(g);
		drawBlock(g, player.getBlock());
		drawBuffo(g, player.getTablero().getBuffo());
		g.setColor(Color.BLACK);
		g.drawString("Puntuacion:" + String.valueOf(player.getPuntuacionBLoq()),w + (int) sizeData/11, y);
		g.drawString("Tiempo:" + String.valueOf(player.getTiempo()),w + (int) sizeData/11, y+ sizeData);
		

	}
	
	private void drawBuffo(Graphics g, Buffo buffo) {
		if(buffo != null) {
		drawGridSquare(g, buffo.getColor(), buffo.getX()*squareSize, buffo.getY()*squareSize, Color.black);
		}
		
		
	}

	private void drawMatriz(Graphics g) {
		for(int j = 0; j < Tablero.filas; j++) {
			for(int i = 0; i < Tablero.cols; i++) {
				g.drawRect(i*squareSize,j*squareSize,squareSize, squareSize);
			}
		}
	}
	
	private void drawBackground(Graphics g) {
		
		Color color;
		Reborde reb;
		
		for(int r = Tablero.filas-1; r >= 0; r--) {
			for(int c = Tablero.cols-1; c >= 0 ; c--) {
				color = player.getColorBg(r,c);
				reb = player.getRebordeBg(r,c);
				
				if(color != null) {
					int x = c * squareSize;
					int y = r * squareSize;
					if(reb != null) drawGridSquare(g,color,x,y,reb.getColor());
					else drawGridSquare(g,color,x,y,Color.black);
				}
			}
		}
		
	}
	
	private void drawGridSquare(Graphics g, Color color, int x, int y, Color colorReborde) {
		
		g.setColor(color);
		g.fillRect(x,y,squareSize, squareSize);
		g.setColor(colorReborde);
		g.drawRect(x,y,squareSize, squareSize);
		
	}
	
	private void  drawBlock(Graphics g, BloqueTetris block) {
		if(block != null) {
			int[][] shape = block.getShape();
			
			for(int fila = 0; fila < block.getHeight(); fila++) {
				for(int col = 0; col < block.getWidth(); col++) {
					if(shape[fila][col] == 1) {
						
						int x = (block.getX() + col) * squareSize;
						int y = (block.getY() + fila) * squareSize;
						
						drawGridSquare(g, block.getColor(), x, y, block.getReborde().getColor());
						
					}
				}
			}
						
		}

	}


	public void startGame() {
		
		Timer timer = new Timer();
		TimerTask actualice = new TimerTask() {
			public void run() {
				if(!player.isAlive()){
					actualice();
					this.cancel();
					timer.cancel();
				}
				if(cancelado()) player.registrePuntuacion(); 
				else actualice();
			}
			
			private boolean cancelado() {
					if(!player.isAlive()) {
						return true;
				}
				return false;
			}
		};
		timer.schedule(actualice,0,100);
		player.run();
	}
	

	public void actualice() {
		repaint();
	}

	public Player getPlayer() {
		return player;
	}

	public boolean isAlive() {
		return player.isAlive();
	}

	public void close() {
		player.end();
		
	}
	


	
	



	
}
