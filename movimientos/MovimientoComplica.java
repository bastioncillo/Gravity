package pr3.movimientos;

import pr3.logica.Ficha;
import pr3.logica.Tablero;

public class MovimientoComplica extends Movimiento {
	private boolean huecoHecho = false;
	private Ficha fichaPerdida = Ficha.VACIA;
	
	// -------------- CONSTRUCTORA ------------------ //
	public MovimientoComplica(int fila, int col, Ficha turno) {
		super(fila, col, turno);
	}
	
	// ---------------------------------- ESTABLECER FILA --------------------------------------
	// Comprueba si hay fichas en la columna para decidir en quï¿½ fila introducir la nueva ficha.
	private int establecerFila(int col, Tablero tab) {
		boolean encontrado = false;
		int fila = 0;
		while(!encontrado && fila < tab.getAlto()){
			// En el caso de que se encuentre una ficha blanca o negra en la columna introducida, se coloca la siguiente justo encima.
			if (tab.getFicha(col, fila) == Ficha.BLANCA || tab.getFicha(col, fila) == Ficha.NEGRA){
				encontrado = true;
				fila--;
			}
			// Si no se encuentra ninguna ficha en toda la columna, se coloca la ficha en la casilla de mï¿½s abajo.
			else if (fila == tab.getAlto() - 1){
				encontrado = true;
			}
			// Si no se encuentra una ficha en esa fila, se pasa a la siguiente.
			else {
				fila++;
			}
		}
		return fila;
	}
	
	//-------------------------- HACER HUECO ---------------------------------------
	// Desplaza todas las ficha de la columna una posición hacia abajo
	public void hacerHueco(int col, Tablero tab){
		for(int i = tab.getAlto()-1; i > 0; i--){
			Ficha aux = tab.getFicha(col, i - 1);
			tab.colocarFicha(col, i, aux);
		}
	}
	
	//-------------------------- DESHACER HUECO ---------------------------------------
	// Desplaza todas las ficha de la columna una posición hacia abajo
	public void deshacerHueco(int col, Tablero tab){
		for(int i = 0; i < tab.getAlto() - 1; i++){
			Ficha aux = tab.getFicha(col, i+1);
			tab.colocarFicha(col, i, aux);
		}
	}
	
	// ---------------------------------- EJECUTA MOVIMIENTO ----------------------------------------
	// Coloca la ficha que le toca en el tablero. 
	public void ejecutaMovimiento(Tablero tab) {
		//Primero establecemos la fila donde colocar la ficha.
		this.fila = establecerFila(this.col, tab);
		//Luego se coloca la ficha en el tablero.
		if(this.fila >= 0){
			this.huecoHecho = false;
			tab.colocarFicha(this.col, this.fila, this.turno);
		}
		//Si no hay sitio en la columna elegida, las fichas se desplazan hacia abajo una posición.
		else{
			//Guardamos la ficha que se pierde en una atributo privado
			int alto =  tab.getAlto();
			this.fichaPerdida = tab.getFicha(this.col, alto-1);
			hacerHueco(this.col, tab);
			//Recordamos que se ha hecho hueco para usarlo luego en el "undo"
			this.huecoHecho = true;
			tab.colocarFicha(this.col, 0, this.turno);
		}
	}
	
	// --------------------------------------- UNDO --------------------------------------------------
	// Deshace el último movimiento realizado
	public void undo(Tablero tab) {
		if(this.huecoHecho){
			//Solo utiliza este proceso si se había tenido que desplazar la columna
			deshacerHueco(this.col, tab);
			int tope = tab.getAlto();
			tab.colocarFicha(this.col, tope-1, this.fichaPerdida);
		}
		else{
			tab.colocarFicha(this.col, this.fila, Ficha.VACIA);
		}
	}
}
