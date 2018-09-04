package pr3.control.factorias;

import java.util.Scanner;
import pr3.juego.ReglasJuego;
import pr3.jugadores.Jugador;
import pr3.logica.Ficha;
import pr3.movimientos.Movimiento;

public abstract class FactoriaJuego {
	public abstract ReglasJuego creaReglas();
	public abstract Movimiento creaMovimiento(int fila, int col, Ficha color);
	public abstract Jugador creaJugadorAleatorio();
	public abstract Jugador creaJugadorHumano(Scanner sc);
}
