package dominio;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Random;

public class BloqueTetris implements Cloneable, Serializable {
	//C, L, LINE, T, Z
	public static int[][][] formas ={{{1,1}, {1,1}}, {{1,0}, {1,0}, {1,1}}, {{1,1,1,1}},
									{{1,1,1}, {0,1,0}}, {{1,1,0}, {0,1,1}} };
	public static Color[] colores= {Color.yellow, Color.CYAN, Color.blue, Color.pink, Color.ORANGE};
	private static List<BloqueTetris> bloquesShared = new ArrayList<BloqueTetris>();
	private int[][] shape;
	
	private Color color;
	private int x, y;
	protected int[][][] rotaciones;
	private int currentRotation;
	private Reborde reborde = Reborde.randomReborde();
	private boolean isMovible = true;
	private int currentForm;
	
	
	public BloqueTetris(int[][] shape, Reborde r, Color c, int cu) {
		this.reborde = r;
		this.shape = shape;
		this.color = c;
		this.currentForm = cu;
		crearRotaciones();
	}
	

	public int getForm() {
		return currentForm;
	}

	/**
	 * Se encarga de hacer apareces los bloques por encima del tablero y en el centro
	 * @param la anchura del tablero
	 */
	public void spawn(int girdWidth) {
		y = -getHeight();
		x = (girdWidth - getWidth()) / 2;
	}

	
	public void moveDown() {
		if(isMovible ) y++;
	}
	
	public void moveLeft() {
		x--;
	}
	
	public void moveRight() {
		x++;
	}
	
	public int getFinalTablero() {
		return y + getHeight();
	}
	
	public void setPos(int X,int Y) {
		x = X;
		y = Y;	
	}
	
	public int getCurrentRot() {
		 if(currentRotation == 0) return 3;
		return currentRotation-1;
	}
	
	public int getNextRot() {
		 if(currentRotation > 3) return 0;
		return currentRotation;
	}
	
	public int[][] getRotation(int pos) {
		if(pos > 3) pos = 0;
		return rotaciones[pos];
	}
	
	public void changeForm(int index) {
		shape = formas[index];
		crearRotaciones();
	}

	 /**
	 * Crea las rotaciones posibles para cada bloque
	 */
	private void crearRotaciones() {
		rotaciones = new int[4][][];
		for(int i  = 0;  i < 4; i++) {
			int r = shape[0].length;
			int c = shape.length;
			
			rotaciones[i] = new int[r][c];
			
			for(int y = 0; y < r; y++) {
				for(int x = 0; x < c; x++) {
					rotaciones[i][y][x] = shape[c-x-1][y];		
				}
			}
			currentRotation =i;
			shape = rotaciones[currentRotation];			
		}
		currentRotation++;
		
	}
	
	 /**
	 * Usa alguna de las rotaciones disponibles de la ficha
	 */
	public void rotar() {
		if(isRotable()) {
			
			setShape();
		}
	}

	 /**
	 * Valida si la ficha se peude rotar correctamente con respecto al tablero y a otras fichas
	 */
	private boolean isRotable() {
		int aux;
		if(currentRotation > 3) aux = 0; else aux = currentRotation; 
		int[][] next = rotaciones[aux];
		if(x + next[0].length <= Tablero.cols && y + next.length < Tablero.filas) return true;
		return false;
	}
	


	
	public BloqueTetris Clone(){
		try {
			return (BloqueTetris) super.clone();
		} catch (CloneNotSupportedException e) {
			Log.registre(e);
		}
		return null;
	}

	public boolean borrarCercanos() {
		return reborde.borrarCercanos();
	}


	 /**
	 * Retorna las coordenas en las que se encuentra la ficha de la forma x,y
	 * @return las coordenadas
	 */
	public int[][] getCoordenadas() {
		int cont = 0;
		int[][] posiciones = new int[4][];
		int[] aux;	
		for(int r = 0; r < getHeight(); r++) {
			for(int c = 0; c < getWidth(); c++) {
				if(shape[r][c] == 1) {
					aux = new int[2];
					aux[0] = c + x;
					if(r + y < 0) aux[1] = 0; else aux[1] = r + y;
					posiciones[cont] = aux;
					cont++;
				}
			}
		}
		return posiciones;
	}

	 /**
	 * Retorna las coordenadas mas cercanas a la ficha o aun bloque de distancia
	 * @param Arreglo de corrednadas cercanas
	 */
	public int[][] getCoordenadasCercanas() {
		int[][] posiciones = new int[16][2];
		int cont = 0;
		int[] aux;
		for(int[] c :this.getCoordenadas()) {
			aux = new int[2];
			aux[0] = c[0]-1;
			aux[1] = c[1];
			posiciones[cont] = aux;
			cont++;
			aux = new int[2];
			aux[0] = c[0]+1;
			aux[1] = c[1];
			posiciones[cont] = aux;
			cont++;
			aux = new int[2];
			aux[0] = c[0];
			aux[1] = c[1]+1;
			posiciones[cont] = aux;
			cont++;
			aux = new int[2];
			aux[0] = c[0];
			aux[1] = c[1];
			posiciones[cont] = aux;
			cont++;
		}
		return posiciones;
	}

	
	public int getLeftEdge() {return x;}
	public int getRigthEdge() {return x + getWidth();}

	/**
	 * Genera un bloque de tetris aleatorio en forma color y reborde
	 */
	private static BloqueTetris selectRandomBlock() {
		int n = new Random().nextInt(formas.length);   
		Reborde r = Reborde.randomReborde();	
		BloqueTetris b = new BloqueTetris(formas[n],r, colores[n], n);
		return b;
	}


	 /**
	 * Toma un bloque del arreglo de bloques
	 * @param posicion de la cualse quiere tomar
	 * @return el bloque
	 */
	public static BloqueTetris getRandomBlock(int pos) {//======>==================================== meter la secuencia q me salio
			while(pos >= bloquesShared.size()) {
				BloqueTetris bloqueAleatorio = selectRandomBlock();
				bloquesShared.add(bloqueAleatorio);				
			}
		
		return  bloquesShared.get(pos).Clone();
	}
	
	public int[][] getShape(){
		return shape;
	}
	
	private void setShape() {
		if(currentRotation > 3) currentRotation = 0;
		shape = rotaciones[currentRotation];
		currentRotation++;
	}
	
	public void setShape(int pos) {
		if(pos < 4) {
			shape = rotaciones[pos];	
			currentRotation = pos+1;			
		}		
	}
	
	public Color getColor() {
		return color;
	}
	
	public Reborde getReborde() {
		return reborde;
	}
	
	public int getHeight() {
		return shape.length;
	}
	
	public int 	getWidth() {
		return shape[0].length;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}


	public boolean modifyShape() {
		return reborde.modifyShape();
	}


	public void setMovilidad(boolean p) {
		this.isMovible = p;
	}



	public int countCuadrosFila(int pos) {
		int cont = 0;
		for(int n :getShape()[pos]) {
			if(n == 1) cont++;
		}
		return cont;
	}
	
	public int countCuadrosCol(int pos) {
		int cont = 0;
		for(int[] n :getShape()) {
			if(n[pos] == 1) cont++;
		}
		return cont;
	}



	public int[][][] getRotaciones() {
		return this.rotaciones;
	}
	
	
	public void findIdealForm(int[][] data, Color[][] bg, Color col) {
		int indx = 0;
		double[][] dataForms = new double[formas.length*4][3];
		
		for(int i = 0; i < formas.length; i++) {	
			BloqueTetris b = new BloqueTetris(formas[i], null, colores[i], i);
			for(int j = 0; j < b.getRotaciones().length; j++) {
				double[] aux = {i, j, puntuacion(data, b.getRotation(j))}; 
				dataForms[indx] = aux;
				indx++;
			}
		}
		calculateBestRotacion(dataForms);
		while(isBajable(bg, col)) moveDown();
		
		
		
	}

	private boolean isBajable(Color[][] bg, Color col) {//get downest squares, por cada coord de ellos, si hay algo all�
		for(int[] co :this.getDownsetSquares()) {
			try {
				//Si abajo esta vacio, mover abajo
				if( bg[co[1]+1][co[0]] != col) return false;	
			}catch(Exception e) {
				return false;
				}
		}
		return true;
	}

	private int[][] getDownsetSquares() {
		int[][] coords = new int[getWidth()][2];
		int pos = 0;
		for(int c = 0; c < getWidth(); c++) {
			for(int r = getHeight()-1; r >= 0 ; r--) {
				if(shape[r][c] == 1 && r+ y < Tablero.filas && x + c< Tablero.cols) {
					int[] aux = {c+x,r+y};
					coords[pos] =  aux;
					pos++;
					break;				
				}
			}
		}
		return coords;
	}



	private void calculateBestRotacion(double[][] dataForms) {
		int form = 0,rot = 0;
		double max = 0;
		for(int i = 0; i < dataForms.length; i++) {
			if(dataForms[i][2] > max) {
				max =  dataForms[i][2];
				form = (int) dataForms[i][0];
				rot =  (int) dataForms[i][1];
			}
		}
		this.changeForm(form);
		this.setShape(rot);
		
	}



	private double puntuacion(int[][] data, int[][] rot) {
			int pos = 0;
			double[] puntuaciones = new double[100]; 
			for(int fil = rot.length-1; fil > 0;fil--) {
				for(int fil2 = data.length-1; fil2 >= 0; fil2--) {
					if(data[fil2] != null) {
						for(int X = 0; X <= data[fil2].length - rot[fil].length; X++)
							if(isValid(rot[fil], Arrays.copyOfRange(data[fil2],X,X+rot[fil].length))) {
								puntuaciones[pos] = calcularPuntuacion(rot, data, X, fil2);
								pos++;
							}	
					}					}

			}
			DoubleSummaryStatistics stat = Arrays.stream(puntuaciones).summaryStatistics();
		return stat.getMax();
	}



	private int areEquals(int[] arr, int[] arr2, int suma) {
		int cont = 0; 
		for(int i = 0; i < arr.length; i++) if(arr[i] == arr2[i] && arr[i] == 1) cont += suma;
		return cont;
	}

	/*
	 * Retorna si los arreglos son validos
	 */
	private boolean isValid(int[] arr, int[] arr2) {
		for(int i = 0; i < arr.length; i++) if((arr[i] == 0 || arr2[i] == 0) && arr[i] != arr2[i]) return false;
		return true;
	}



	/*
	 * Calcula Una puntuacion que determinara que tan buena es la rotacion
	 */
	private int calcularPuntuacion(int[][] rot, int[][] data, int col, int fil) {//================ ARREGLAR==============================
		//Retornar un porcentaje de parecido, la que tenga un mayor porcentaje de parecido es la indicada
		int puntuacion = 0;
		for(int i = rot.length-1; i >= 0; i--) {
			if(fil >= 0 && col+rot[i].length <= rot[i].length)
			{
				puntuacion += areEquals(rot[i], Arrays.copyOfRange(data[fil], col, col+rot[i].length), fil+1); 
			}
			fil--;
		}
		return puntuacion;
	}

	/*
	 * Traduce una matriz de colores a una matriz de ceros y unos
	 * 1 si ahi puede caber una ficha, 0 si no.
	 */

	public int[][] traducir(Color[][] bg, Color col) {
		int[][] rta = new int[5][];
		int cont = 0;
		for(int i = y; i < Tablero.filas && i < y + 4; i++) {
			int[] aux = new int[Tablero.cols-x];
			for(int j =x; j < Tablero.cols && j <x + 4; j++) {
				if(bg[i][j] == col) aux[j-x] = 1;  else aux[j-x] = 0;	
			}
			rta[cont] = aux;
			cont++;
		}
		return rta;
	}
	
	
	
}

