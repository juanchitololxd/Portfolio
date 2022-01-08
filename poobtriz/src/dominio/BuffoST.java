package dominio;

import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;

public class BuffoST extends Buffo{
	public BuffoST(int[] c) {
		super(c);
		super.c = Color.yellow;
		// TODO Auto-generated constructor stub
	}

	public void activate(Tablero t) {
		//Hace que el bloque no baje automáticamente por 3 segundos

		Timer timer = new Timer();
		int beforeVel = t.getVelocidad();
		t.setVelocidad(3000);
		TimerTask genBuffo = new TimerTask() {		
			public void run() {
				t.setVelocidad(beforeVel);
				this.cancel();
				timer.cancel();
				
			}
		};
		timer.schedule(genBuffo,0);
	}
	
	
}
