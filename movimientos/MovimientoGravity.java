package pr3.movimientos;

import pr3.logica.Ficha;
import pr3.logica.Tablero;

public class MovimientoGravity extends Movimiento {
	
	// ------------ CONSTRUCTORA -------------- //
	public MovimientoGravity(int fila, int col, Ficha turno) {
		super(fila, col, turno);
	}

	// --------------- EJECUTAR MOVIMIENTO -------------- //
	public void ejecutaMovimiento(Tablero tab) {
		 int distV = 0, distH = 0;
	        int auxV = 0, auxH = 0;
	        // Se comprueba si la ficha debe moverse en vertical o en horizontal...
	        // Para ello se compara la distancia hasta ambos extremos horizontalmente y se coge la menor.
	        if ((tab.getAncho()-1) - this.col > this.col) {
	            distH = this.col;
	            auxH = (tab.getAncho()-1) - this.col;
	        }
	        else { 
	            distH = (tab.getAncho()-1) - this.col; 
	            auxH = this.col;
	        }
	        // Se repite el proceso verticalmente...
	        if ((tab.getAlto()-1) - this.fila > this.fila) {
	            distV = this.fila;
	            auxV = (tab.getAlto() - 1) - this.fila;
	        }
	        else {
	            distV = (tab.getAlto()-1) - this.fila;
	            auxV = this.fila;
	        }
	        // A continuación se comparan ambas distancias y se mueve la ficha hacia la menor...
	        if (distH > distV) {
	            // La ficha se mueve verticalmente.
	            if((tab.getAlto()-1) - this.fila > distV){
	                // La ficha se mueve hacia arriba.
	                 movimiento(-1, 0, tab);
	            }
	            //La ficha se mueve hacia abajo.
	            else if((tab.getAlto()-1) - this.fila <= distV){
	                if (auxV == distV) {
	                    movimiento(0, 0, tab);
	                }
	                else movimiento(1, 0, tab);
	            }
	        }
	        else if (distH < distV) {
	            // La fivha se mueve horizontalmente.
	            if((tab.getAncho()-1) - this.col > distH){
	                // La ficha se mueve hacia la izquierda.
	                movimiento(0, -1, tab);
	            }
	            // La ficha se mueve hacia la derecha.
	            else if((tab.getAncho()-1) - this.col <= distH){
	                if (distH == auxH) {
	                    movimiento(0, 0, tab);
	                }
	                else movimiento(0, 1, tab);
	            }
	        }
	        // En caso de que ambas sean iguales, se debe mover en ambas direcciones...
	        else if (distH == distV) {
	            // Si la fila y la columna están en la parte superior izquierda del tablero...
	            if((tab.getAlto()-1) - this.fila > distV && (tab.getAncho()-1) - this.col > distH){
	                //La ficha se mueve arriba y a la izquierda.
	                movimiento(-1, -1, tab);
	            }
	            // Si la fila y la columna están en la parte inferior izquierda del tablero...
	            else if((tab.getAlto()-1) - this.fila <= distV && (tab.getAncho()-1) - this.col > distH){
	                // La ficha se mueve abajo y a la izquierda.
	                movimiento(1, -1, tab);
	            }
	            // Si la fila y la columna están en la parte inferior derecha del tablero...
	            else if((tab.getAlto()-1) - this.fila <= distV && (tab.getAncho()-1) - this.col <= distH){
	                if (auxH == distH && auxV == distV) {
	                    movimiento(0, 0, tab);
	                }
	                // La ficha se mueve abajo y a la derecha
	                else movimiento(1, 1, tab);
	            }
	            // Si la fila y la columna están en la parte superior derecha del tablero...
	            else if((tab.getAlto()-1) - this.fila > distV && (tab.getAncho()-1) - this.col <= distH){
	                // La ficha se mueve arriba y a la derecha.
	                movimiento(-1, 1, tab);
	            }
	        }
	        // Una vez encontrada la posición correcta, se coloca la ficha en el tablero.
	        tab.colocarFicha(this.col, this.fila, this.turno);
	    }
		
	 // ---------------------- MOVIMIENTO ----------------------- //
    private void movimiento(int DesY, int DesX, Tablero tab){
        // La ficha solo se desplaza hacia abajo.
        if(DesX == 0 && DesY == 1){
            /* Y mantiene ese rumbo mientras no se encuentre con otro ficha,
             * en cuyo caso para de avanzar*/
            while(this.fila < (tab.getAlto()-1) && tab.getFicha(this.col, this.fila+1) == Ficha.VACIA){
                this.fila++;
            }
        }
        // La ficha solo se desplaza hacia arriba.
        else if(DesX == 0 && DesY == -1){
            /* Y mantiene ese rumbo mientras no se encuentre con otro ficha,
             * en cuyo caso para de avanzar*/
            while(this.fila > 0 && tab.getFicha(this.col, this.fila-1) == Ficha.VACIA){
                this.fila--;
            }
        }
        // La ficha solo se desplaza hacia la derecha.
        else if(DesX == 1 && DesY == 0){
            /* Y mantiene ese rumbo mientras no se encuentre con otro ficha,
             * en cuyo caso para de avanzar*/
            while(this.col < (tab.getAncho()-1) && tab.getFicha(this.col+1, this.fila) == Ficha.VACIA){
                this.col++;
            }   
        }
        // La ficha solo se desplaza hacia la izquierda.
        else if(DesX == -1 && DesY == 0){
            /* Y mantiene ese rumbo mientras no se encuentre con otro ficha,
             * en cuyo caso para de avanzar*/
            while( this.col > 0 && tab.getFicha(this.col-1, this.fila) == Ficha.VACIA){
                this.col--;
            }
        }
        // La ficha se desplaza hacia abajo y hacia la derecha.
        else if(DesX == 1 && DesY == 1){
            /* Y mantiene ese rumbo mientras no se encuentre con otro ficha,
             * en cuyo caso para de avanzar*/
            while(this.col < (tab.getAncho()-1) && this.fila < (tab.getAlto()-1) && tab.getFicha(this.col+1, this.fila+1) == Ficha.VACIA){
                this.col++;
                this.fila++;
            }       
        }
        // La ficha se desplaza hacia arriba y hacia la derecha.
        else if(DesX == 1 && DesY == -1){
            /* Y mantiene ese rumbo mientras no se encuentre con otro ficha,
             * en cuyo caso para de avanzar*/
            while(this.col < (tab.getAncho()-1) && this.fila > 0 && tab.getFicha(this.col+1, this.fila-1) == Ficha.VACIA){
                this.col++;
                this.fila--;
            }
        }
        // La ficha se desplaza hacia abajo y hacia la izquierda.
        else if(DesX == -1 && DesY == 1){
            /* Y mantiene ese rumbo mientras no se encuentre con otro ficha,
             * en cuyo caso para de avanzar*/
            while( this.col > 0 && this.fila < (tab.getAlto()-1) && tab.getFicha(this.col-1, this.fila+1) == Ficha.VACIA){
                this.col--;
                this.fila++;
            }
        }
        // La ficha se desplaza hacia arriba y hacia la izquierda.
        else if(DesX == -1 && DesY == -1){
            /* Y mantiene ese rumbo mientras no se encuentre con otro ficha,
             * en cuyo caso para de avanzar*/
            while(this.col > 0 && this.fila > 0 && tab.getFicha(this.col-1, this.fila-1) == Ficha.VACIA){
                this.col--;
                this.fila--;
            }
        }
    }
	
	// ------------------ DESHACER -------------- //
	public void undo(Tablero tab) {
		tab.colocarFicha(this.col, this.fila, Ficha.VACIA);
	}		
}