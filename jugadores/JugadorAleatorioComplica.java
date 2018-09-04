package pr3.jugadores;

import java.util.Random;
import pr3.control.factorias.FactoriaJuego;
import pr3.logica.Ficha;
import pr3.logica.Tablero;
import pr3.movimientos.Movimiento;

public class JugadorAleatorioComplica extends Jugador{
	// ------------ OBTENER MOVIMIENTO ALEATORIO ---------------- //
	public Movimiento getMovimiento(FactoriaJuego factoria, Tablero tab, Ficha color) {
		int columna = 0;
		Random random = new Random();
		//Creación de la columna aleatoria dentro de los márgenes del tablero
		columna = random.nextInt(tab.getAncho() - 1);
		return factoria.creaMovimiento(0, columna, color);
	}
}
