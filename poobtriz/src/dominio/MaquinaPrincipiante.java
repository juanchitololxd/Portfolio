package dominio;

import java.util.Random;

public class MaquinaPrincipiante extends Maquina{

	public MaquinaPrincipiante(Tablero t) {
		super("MaquinaNoobMasterCrack99", t);
	}

	@Override
	protected void moverFicha() {
		
		if(t.getBlock() != null) {
			int n = new Random().nextInt(3);
			
			switch(n) {
				case 2:
					t.setMovilidadBlock(true);
					break;
				case 1:
					t.moveBlockLeft();
					break;
					
				case 0:
					t.moveBlockRight();
					break;
			}			
		}

		
			
		
	}
	

}
