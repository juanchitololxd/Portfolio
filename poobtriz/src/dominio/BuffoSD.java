package dominio;

import java.awt.Color;

public class BuffoSD extends Buffo{
	
	public BuffoSD(int[] c) {
		super(c);
		super.c = Color.magenta;
	}

	
	public void activate(Tablero t) {
		//Detiene el bloque y seguir� bajando cuando el jugador presione la tecla de bajar.
		t.setMovilidadBlock(false);
		
	}

}
