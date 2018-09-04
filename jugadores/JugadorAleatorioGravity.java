package pr3.jugadores;

import java.util.Random;
import pr3.control.factorias.FactoriaJuego;
import pr3.logica.Ficha;
import pr3.logica.Tablero;
import pr3.movimientos.Movimiento;

public class JugadorAleatorioGravity extends Jugador{
	// ------------ OBTENER MOVIMIENTO ALEATORIO ---------------- //
	public Movimiento getMovimiento(FactoriaJuego factoria, Tablero tab, Ficha color) {
		int columna = 0, fila = 0;
		Random random = new Random();
		do{
			columna = random.nextInt(tab.getAncho() - 1);
			fila = random.nextInt(tab.getAlto() - 1);
		}while(tab.getFicha(columna, fila) != Ficha.VACIA);
		return factoria.creaMovimiento(fila, columna, color);
	}

}