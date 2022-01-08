package dominio;

import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;

public class BuffoS extends Buffo{

	public BuffoS(int[] c) {
		super(c);
		super.c = Color.green;
		// TODO Auto-generated constructor stub
	}
	
	public void activate(Tablero t) {
		//Las fichas bajan mas lento por 3 segundos
		Timer timer = new Timer();
		t.setVelocidad(t.getVelocidad()*2);
		
		TimerTask genBuffo = new TimerTask() {
			public void run() {
					t.setVelocidad((int) t.getVelocidad()/2);
					this.cancel();
					timer.cancel();
			}
		};
		timer.schedule(genBuffo,3000,3000);
		
	}
	

}
