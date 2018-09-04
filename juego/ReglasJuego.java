package pr3.juego;

import pr3.logica.Ficha;
import pr3.logica.Tablero;
import pr3.movimientos.Movimiento;

public abstract class ReglasJuego {
	public abstract Tablero iniciarTablero();
	public abstract Ficha comprobarGanador(Tablero tab, Movimiento mov);
	public abstract boolean hayTablas(Tablero tab, Movimiento mov);
	public abstract Ficha siguienteTurno(Tablero tab, Ficha turno);
	public abstract String dibujarTablero(Tablero tab);
}
