package pr3.juego;

import pr3.logica.Ficha;
import pr3.logica.Tablero;
import pr3.movimientos.Movimiento;

public class ReglasComunes4EnLinea {
	
	//------------------------------------- COMPROBAR GANADOR NEGRAS ------------------------------------
	//Comprueba si las fichas negras han hecho 4 en raya
	public static boolean c4Negras(Tablero tab){
		boolean c4 = false;
		/*Como solo queremos que mire el tablero hasta que haya cuatro en linea, usamos un bucle "while"
		 * que imita un bucle "for", y que nos permite salir antes de llegar al tope del recorrido*/
		int i = 0;
		while(i < tab.getAlto() && !c4){
			int j = 0;
			while(j < tab.getAncho() && !c4){
				//Solo realiza la comprobación si las fichas son negras
				if(tab.getFicha(j, i) == Ficha.NEGRA){
					if(comprobarFila(j, i, tab)){
						c4 = true;
					}
					else if(comprobarColumna(j, i, tab)){
						c4 = true;
					}
					else if(comprobarDiagonalAscendente(j, i, tab)){
						c4 = true;
					}
					else if(comprobarDiagonalDescendente(j, i, tab)){
						c4 = true;
					}
					else{
						c4 = false;
					}
				}
				j++;
			}
			i++;
		}
		return c4;
	}
		
	//------------------------------------- COMPROBAR GANADOR BLANCAS ------------------------------------
	//Comprueba si las fichas blancas han hecho 4 en raya
	public static boolean c4Blancas(Tablero tab){
		boolean c4 = false;
		/*Como solo queremos que mire el tablero hasta que haya cuatro en linea, usamos un bucle "while"
		 * que imita un bucle "for", y que nos permite salir antes de llegar al tope del recorrido*/
		int i = 0;
		while(i < tab.getAlto() && !c4){
			int j = 0;
			while(j < tab.getAncho() && !c4){
				//Solo realiza la comprobación si las fichas son blancas
				if(tab.getFicha(j, i) == Ficha.BLANCA){
					if(comprobarFila(j, i, tab)){
						c4 = true;
					}
					else if(comprobarColumna(j, i, tab)){
						c4 = true;
					}
					else if(comprobarDiagonalAscendente(j, i, tab)){
						c4 = true;
					}
					else if(comprobarDiagonalDescendente(j, i, tab)){
						c4 = true;
					}
					else{
						c4 = false;
					}
				}
				j++;
			}
			i++;
		}
		return c4;
	}
	
	// -------------------------------------- COMPROBAR FILA --------------------------------------------
	// Comprueba si se han enlazado cuatro fichas del mismo color en una fila del tablero.
	public static boolean comprobarFila(int col, int fila, Tablero tab){
		boolean hayCuatro = false;
		int contFichas = 1, colAux = col;
		// Va sumando mientras las fichas en columnas consecutivas sean iguales y no se salga del tablero...
		while (!hayCuatro && (colAux < tab.getAncho() - 1) && (tab.getFicha(col, fila) == tab.getFicha(colAux+1, fila))) {
			contFichas++;
			colAux++;
			// Si encuentra 4 fichas seguidas, se pone a true.
			if(contFichas == 4){
				hayCuatro = true;
			}
		}
		//Reiniciamos colAux a su valor original.
		colAux = col;
		// Repite el procedimiento con columnas anteriores.
		while(!hayCuatro && (colAux > 0) && (tab.getFicha(col, fila) == tab.getFicha(colAux-1, fila)) ){
			contFichas++;
			colAux--;
			if(contFichas == 4){
				hayCuatro = true;
			}
		}
		return hayCuatro;
	}
		
	// ----------------------------------- COMPROBAR COLUMNA ----------------------------------
	// Comprueba si hay cuatro fichas del mismo color en una columna del tablero.
	public static boolean comprobarColumna(int col, int fila, Tablero tab){
		boolean hayCuatro = false;
		int contFichas = 1, filaAux = fila;
		// Va sumando mientras las fichas en filas consecutivas sean del mismo color y no se salga del tablero...
		while (!hayCuatro && (filaAux < tab.getAlto() - 1) && (tab.getFicha(col, fila) == tab.getFicha(col, filaAux+1))) {
			contFichas++;
			filaAux++;
			// Si encuentra cuatro fichas seguidas, se pone a true.
			if(contFichas == 4){
				hayCuatro = true;
			}
		}
		//Reiniciamos filaAux a su valor original
		filaAux = fila;
		// Realiza el mismo procedimiento con filas anteriores.
		while(!hayCuatro && (filaAux > 0) && (tab.getFicha(col, fila) == tab.getFicha(col, filaAux-1))){
			contFichas++;
			filaAux--;
			if(contFichas == 4){
				hayCuatro = true;
			}
		}
		return hayCuatro;
	}
		
	// --------------------------------------- COMPROBAR DIAGONAL DESCENDENTE -------------------------------
	// Comprueba si hay cuatro fichas del mismo color consecutivas en una diagonal del tablero.
	public static boolean comprobarDiagonalDescendente(int col, int fila, Tablero tab){
		boolean hayCuatro = false;
		int contFichas = 1, colAux = col, filaAux = fila;
		// Va sumando si encuentra fichas del mismo color consecutivas avanzando una fila y una columna y no se sale del tablero...
		while(!hayCuatro && (colAux < tab.getAncho() - 1) && (filaAux < tab.getAlto() - 1) && (tab.getFicha(col, fila) == tab.getFicha(colAux+1, filaAux+1))){
			contFichas++;
			colAux++;
			filaAux++;
			// Si encuentra cuatro fichas consecutivas, se pone a true.
			if(contFichas == 4){
				hayCuatro = true;
			}
		}
		//Reiniciamos el valor de colAux y filaAux a su valor original.
		colAux = col;
		filaAux = fila;
		// Realiza el mismo procedimiento con filas y columnas anteriores.
		while(!hayCuatro && (colAux > 0) && (filaAux > 0) && (tab.getFicha(col, fila) == tab.getFicha(colAux-1, filaAux-1))){
			contFichas++;
			colAux--;
			filaAux--;
			if(contFichas == 4){
				hayCuatro = true;
			}
		}
		return hayCuatro;
	}
	
	// --------------------------------------- COMPROBAR DIAGONAL ASCENDENTE -------------------------------
	// Comprueba si hay cuatro fichas del mismo color consecutivas en una diagonal del tablero.
	public static boolean comprobarDiagonalAscendente(int col, int fila, Tablero tab){
		boolean hayCuatro = false;
		int contFichas = 1, colAux = col, filaAux = fila;
		// Va sumando si encuentra fichas del mismo color consecutivas avanzando una fila y una columna y no se sale del tablero...
		while(!hayCuatro && (colAux < tab.getAncho() - 1) && (filaAux > 0) && (tab.getFicha(col, fila) == tab.getFicha(colAux+1, filaAux-1))){
			contFichas++;
			colAux++;
			filaAux--;
			// Si encuentra cuatro fichas consecutivas, se pone a true.
			if(contFichas == 4){
				hayCuatro = true;
			}
		}
		//Reiniciamos el valor de colAux y filaAux a su valor original.
		colAux = col;
		filaAux = fila;
		// Realiza el mismo procedimiento con filas y columnas anteriores.
		while(!hayCuatro && (colAux > 0) && (filaAux < tab.getAlto() - 1) && (tab.getFicha(col, fila) == tab.getFicha(colAux-1, filaAux+1))){
			contFichas++;
			colAux--;
			filaAux++;
			if(contFichas == 4){
				hayCuatro = true;
			}
		}
		return hayCuatro;
	}
	
	// ----------------------------------- CAMBIAR TURNO ---------------------------------
	// Cambia el color de la ficha a introducir según el turno que corresponda.
	public static Ficha siguienteTurno(Tablero tab, Ficha turno) {
		if (turno == Ficha.BLANCA || turno == Ficha.VACIA) {
			turno = Ficha.NEGRA;
		}
		else {
			turno = Ficha.BLANCA;
		}
		return turno;
	}
	
	//-------------------------- COMPROBAR SI HAY TABLAS ----------------------------------
	//Cuenta el número de posiciones ocupadas en el tablero, para ver si hay tablas.
	public static boolean hayTablas(Tablero tab, Movimiento mov) {
		boolean tablas = false;
		int c_ocupadas = 0;
		for(int i = 0; i < tab.getAncho(); i++){
			for(int j = 0; j < tab.getAlto(); j++){
				if(tab.getFicha(i, j) == Ficha.BLANCA || tab.getFicha(i, j) == Ficha.NEGRA){
					c_ocupadas = c_ocupadas + 1;
				}
			}
		}
		if(c_ocupadas == tab.getAncho()*tab.getAlto()){
			tablas = true;
		}
		return tablas;
	}
}
