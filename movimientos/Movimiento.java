package pr3.movimientos;

import pr3.logica.*;

public abstract class Movimiento {
	protected int col;
	protected Ficha turno;
	protected int fila;
		
	public Movimiento(int fila, int col, Ficha turno) {
		this.col = col;
		this.fila = fila;
		this.turno = turno;
	}
	
	public int getCol() {
		return this.col;
	}
	
	public int getFila() {
		return this.fila;
	}
	
	public Ficha getTurno() {
		return this.turno;
	}
	
	public abstract void ejecutaMovimiento(Tablero tab) throws ColumnaLlena;
	public abstract void undo(Tablero tab);
}
