package pr3.jugadores;

import pr3.control.factorias.FactoriaJuego;
import pr3.logica.Ficha;
import pr3.logica.Tablero;
import pr3.movimientos.Movimiento;

public abstract class Jugador {
	public abstract Movimiento getMovimiento(FactoriaJuego factoria, Tablero tab, Ficha color) throws CaracterInvalido, CasillaOcupada, FilaIncorrecta, ColumnaIncorrecta;
}
