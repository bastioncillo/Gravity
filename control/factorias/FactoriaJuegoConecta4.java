package pr3.control.factorias;

import java.util.Scanner;

import pr3.juego.*;
import pr3.jugadores.*;
import pr3.logica.Ficha;
import pr3.movimientos.*;

public class FactoriaJuegoConecta4 extends FactoriaJuego{

	public ReglasJuego creaReglas() {
		return new ReglasConecta4();
	}

	public Movimiento creaMovimiento(int fila, int col, Ficha color) {
		return new MovimientoConecta4(fila, col, color);
	}

	public Jugador creaJugadorAleatorio() {
		return new JugadorAleatorioConecta4();
	}

	public Jugador creaJugadorHumano(Scanner sc) {
		return new JugadorConsolaConecta4(sc);
	}
}
