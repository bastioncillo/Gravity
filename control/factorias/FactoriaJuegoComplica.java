package pr3.control.factorias;

import java.util.Scanner;

import pr3.juego.*;
import pr3.jugadores.*;
import pr3.logica.Ficha;
import pr3.movimientos.*;

public class FactoriaJuegoComplica extends FactoriaJuego{

	public ReglasJuego creaReglas() {
		return new ReglasComplica();
	}

	public Movimiento creaMovimiento(int fila, int col, Ficha color) {
		return new MovimientoComplica(fila, col, color);
	}

	public Jugador creaJugadorAleatorio() {
		return new JugadorAleatorioComplica();
	}

	public Jugador creaJugadorHumano(Scanner sc) {
		return new JugadorConsolaComplica(sc);
	}
}
