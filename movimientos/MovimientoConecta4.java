package pr3.movimientos;

import pr3.logica.Ficha;
import pr3.logica.Tablero;

public class MovimientoConecta4 extends Movimiento {
	
	// --------------------------------- CONSTRUCTORA --------------------------------------
	public MovimientoConecta4(int fila, int col, Ficha turno){
		super(fila, col, turno);
	}
	
	// ---------------------------------- ESTABLECER FILA --------------------------------------
	// Comprueba si hay fichas en la columna para decidir en qué fila introducir la nueva ficha.
	private int establecerFila(int col, Tablero tab) {
		boolean encontrado = false;
		int fila = 0;
		while(!encontrado && fila < tab.getAlto()){
			// En el caso de que se encuentre una ficha blanca o negra en la columna introducida, se coloca la siguiente justo encima.
			if (tab.getFicha(col, fila) == Ficha.BLANCA || tab.getFicha(col, fila) == Ficha.NEGRA){
				encontrado = true;
				fila--;
			}
			// Si no se encuentra ninguna ficha en toda la columna, se coloca la ficha en la casilla de más abajo.
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
	
	// ---------------------------------- EJECUTA MOVIMIENTO ----------------------------------------
	// Coloca la ficha que le toca en el tablero, en el caso de que se pueda. 
	public void ejecutaMovimiento(Tablero tab) throws ColumnaLlena{
		//Primero establecemos la fila donde colocar la ficha.
		this.fila = establecerFila(this.col, tab);
		//Luego se coloca la ficha en el tablero, si hay sitio en la columna elegida.
		if(this.fila >= 0){
			tab.colocarFicha(this.col, this.fila, turno);
		}
		else throw new ColumnaLlena("Columna llena");
	}
	
	// --------------------------------------- UNDO --------------------------------------------------
	// Deshace el último movimiento realizado
	public void undo(Tablero tab) {
		tab.colocarFicha(this.col, this.fila, Ficha.VACIA);
	}
}
