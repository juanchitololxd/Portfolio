package dominio;


public class MaquinaPro extends Maquina {
	private int xPos;
	private int rotacion = 0;
	private BloqueTetris b = null;
	
	public MaquinaPro(Tablero t) {
		super("MAQUINA PRO", t);
	}

	@Override
	protected void moverFicha() {
		try {
			 if(t.getBlock() != b) {//Realizar los calculos una vez por bloque
				 b = t.getBlock(); 
				 if(t.getBlock().getReborde() instanceof RebordeR) realizarCalculosBomb();
				 else realizarCalculos();	
			}

				if(t.getBlock().getCurrentRot() != rotacion) {
					t.rotarBlock();		
				}
				if(t.getBlock().getX() > xPos) {
					t.moveBlockLeft();
				}else if(t.getBlock().getX() < xPos) {
					t.moveBlockRight();					
				} 
				t.setMovilidadBlock(true);
			}catch(Exception e) {Log.registre(e);}
		}

		


	private void realizarCalculosBomb() {
		boolean ok = brokeAGap();
		if(!ok) ok = killUseless();
		if(!ok) xPos = Tablero.cols - t.getBlock().getWidth();
	}

	

private boolean killUseless() {
	boolean fin = false;
	for(int i =0; i  < Tablero.filas -1; i++) {
		for(int j = 0; j <  Tablero.cols; j++) {
			if(super.getRebordeBg(i, j) instanceof RebordeP) {
				int[][] data = obtenerData(i,j);
				evaluateBestRotacion(data);
				fin = true;
				break;
			}
		}
		if(fin) break;
	} 
	return fin;
	
}

private boolean brokeAGap() {
	boolean fin = false;
	for(int i =0; i  < Tablero.filas -1; i++) {
		for(int j = 0; j <  Tablero.cols; j++) {
			try {
				if(super.getColorBg(i, j) == t.getBg() && !isAccesible(i,j)) {
					int[][] data = obtenerData(i,j);
					evaluateBestRotacion(data);
					fin = true;
					break;
				}					
			}catch(Exception e) {continue;}

		}
		if(fin) break;
	} 
	return fin;
}

public void realizarCalculos() {
	boolean fin = false;
	for(int i = Tablero.filas -1; i  >= 0; i--) {
		for(int j = 0; j <  Tablero.cols; j++) {
			try {
				if(super.getColorBg(i, j) == t.getBg() && isAccesible(i,j)) {
					int[][] data = obtenerData(i,j);
					if(data != null) {
						evaluateBestRotacion(data);
						fin = true;
						break;
					}
				}					
			}catch(Exception e) {continue;}

		}
		if(fin) break;
	}
}




	private void evaluateBestRotacion(int[][] data) {
	int actual = 0;
	for(int i = 0; i < data.length; i++) {
		int[] rot = data[i];
		if(rot[2] == 0 && rot[1] == 0) break;
		if(rot[1] > data[actual][1]) actual = i;	
		else if(rot[1] == data[actual][1] && rot[2] > data[actual][2]) actual = rot[0];

	}
	
	xPos = data[actual][3];
	rotacion =data[actual][0];
}
	
	
	private int[][] obtenerData(int y, int x) {
		int[][] posibilities = new int[16][4];
		int cont = 0;
		for(int i = 0; i < 4; i++) {
			BloqueTetris copy = t.getBlock().Clone();
			copy.setShape(i);
			
			for(int a = -2; a < 2; a++) {
				try {
					copy.setPos(x+a, y - copy.getHeight()+1);
					boolean cumple = t.isValidPosition(copy);
					if(cumple) {
						int[] aux = {i,copy.countCuadrosFila(copy.getShape().length-1),copy.countCuadrosCol(0),x+a};
						posibilities[cont] = aux;
						cont++;
					}
				}catch(Exception e) {continue;}

		}
			
	
			
	}
		if(cont == 0) return null;
		return posibilities;
	}

	private boolean isAccesible(int i, int j) {
		for(int x = i; x >= 0; x--)
			if(super.getColorBg(x, j) != t.getBg()) return false;
		return true;
	}
}
