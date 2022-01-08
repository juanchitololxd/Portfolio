package dominio;

import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;


public abstract class Maquina extends Player {
	private int timeMove = 450;
	
	public Maquina(String name, Tablero t) {
		super(name, t);
	}
	
	public void run() {
		try {
			t.start();
			Timer timer = new Timer();
			TimerTask mover = new TimerTask() {
				public void run() {
					if(!t.isAlive()){
						this.cancel();
						timer.cancel();
					}else{
						if(t.getBlock() != null) moverFicha();
					}	
				}
			};
			timer.schedule(mover,0,timeMove);
				
		}catch(Exception e) {
			e.printStackTrace();
			Log.registre(e);
		}
	}
	 /**
	 * Mueve el bloque segun el el tipo de maquina
	 */
	protected abstract void moverFicha();
	
	@Override
	public void registrePuntuacion() {
		// TODO Auto-generated method stub
		
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
		if( e.getKeyCode() == KeyEvent.VK_RIGHT) {t.moveBlockRight();}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT) {t.moveBlockLeft();}
		else if(e.getKeyCode() == KeyEvent.VK_UP) {t.rotarBlock();}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN) {try {
			t.setMovilidadBlock(true);
			t.moveBlockDown();
		} catch (TetrisException e1) {
			Log.registre(e1);
		}}
		
	}
	

	
}
