package dominio;

import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;

public class BuffoX extends Buffo{
	public BuffoX(int[] c) {
		super(c);
		super.c = Color.blue;
		// TODO Auto-generated constructor stub
	}
	@Override
	public void activate(Tablero t) {
		//Durante 3 segundos la ficha bajará al doble de velocidad normal.
		Timer timer = new Timer();
		t.setVelocidad((int) t.getVelocidad()/2);
		
		TimerTask genBuffo = new TimerTask() {
			public void run() {
				t.setVelocidad(t.getVelocidad()*2);
				this.cancel();
				timer.cancel();
			}
		};
		timer.schedule(genBuffo,3000);
		
	}
}
