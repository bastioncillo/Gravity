package pr3.control.factorias;

import java.util.Scanner;
import pr3.juego.*;
import pr3.jugadores.*;
import pr3.logica.Ficha;
import pr3.movimientos.*;

public class FactoriaJuegoGravity extends FactoriaJuego{
	private int alto, ancho;
	
	public FactoriaJuegoGravity(int alto, int ancho){
		this.alto = alto;
		this.ancho = ancho;
	}
		
	public ReglasJuego creaReglas() {
		return new ReglasGravity(this.alto, this.ancho);
	}

	public Movimiento creaMovimiento(int fila, int col, Ficha color) {
		return new MovimientoGravity(fila, col, color);
	}

	public Jugador creaJugadorAleatorio() {
		return new JugadorAleatorioGravity();
	}

	public Jugador creaJugadorHumano(Scanner sc) {
		return new JugadorConsolaGravity(sc);
	}
}
